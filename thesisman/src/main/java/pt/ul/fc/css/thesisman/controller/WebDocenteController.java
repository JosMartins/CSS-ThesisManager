package pt.ul.fc.css.thesisman.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pt.ul.fc.css.thesisman.business.services.MestradoDTO;
import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.ApplicationException;
import pt.ul.fc.css.thesisman.entities.Docente;
import pt.ul.fc.css.thesisman.service.DefesaPropostaTeseService;
import pt.ul.fc.css.thesisman.service.DocenteService;

import org.springframework.ui.Model;

import pt.ul.fc.css.thesisman.business.services.DefesaPropostaTeseDTO;
import pt.ul.fc.css.thesisman.business.services.DocenteDTO;
import pt.ul.fc.css.thesisman.service.MestradoService;
import pt.ul.fc.css.thesisman.service.TemaService;

import java.util.List;
import java.util.Optional;


@Controller
public class WebDocenteController {

    Logger logger = LoggerFactory.getLogger(WebDocenteController.class);

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private TemaService temaService;

    @Autowired
    private MestradoService mestradoService;
    
    @Autowired
    private DefesaPropostaTeseService candPropostaTeseService;

    public WebDocenteController() {
        super();
    }


    @GetMapping("/docente/{id}/main")
    public String docenteMain(final Model model, @PathVariable Long id) {
        logger.debug("GET - from /docente/{}/main", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent()) {
            if (d.get().getLogin()) {
                model.addAttribute("docente", d.get());
            } else {
                return "error/401";
            }

            return "docente_main_view";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/docentes/login")
    public String docenteLogin(final Model model, @RequestParam(value = "b", required = false) String b) {
        logger.debug("GET - from /docente/login");
        if (Boolean.parseBoolean(b)) {
            model.addAttribute("wrongPass", "Password Errada.");
        }
        model.addAttribute("docente", new Docente());
        return "docente_login";
    }

    @PostMapping("/docentes/login")
    public String docenteLoginAction(final Model model, @ModelAttribute DocenteDTO d) {
        logger.debug("POST - from /docentes/login");
        try {
            Optional<DocenteDTO> d2 = docenteService.login(d.getUsername(), d.getPassword());
            return d2.map(docenteDTO -> "redirect:/docente/" + docenteDTO.getId() + "/main").orElse("redirect:/docentes/login?b=true");

        } catch (ApplicationException e) {
            model.addAttribute("docente", new DocenteDTO());
            model.addAttribute("error", e.getMessage());
            return "docente_login";

        }
    }

    @GetMapping("/docente/{id}/temas")
    public String getTemas(final Model model, @PathVariable Long id) {
        logger.debug("GET - from /docentes/{}/temas", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            model.addAttribute("temas", temaService.getTemas());

            return "docente_temas";
        } else {
            return d.isPresent() ? "error/401" : "error/404";
        }
    }


    @GetMapping("/docente/{id}/temas/registar")
    public String submeterTema(final Model model, @PathVariable Long id) {
        logger.debug("GET - from /docente/{}/temas/submeter", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        List<MestradoDTO> mestrados = mestradoService.getMestrados();
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            model.addAttribute("mestrados", mestrados);
            model.addAttribute("tema", new TemaDTO());
            return "docente_temas_registar";
        } else {
            return d.isPresent() ? "error/401" : "error/404";
        }
    }

    @PostMapping("/docente/{id}/temas/registar")
    public String submeterTemaAction(final Model model, @PathVariable Long id, @ModelAttribute TemaDTO tema, @RequestParam("selectedMestrados") List<Long> mestradoIds) {
        logger.debug("POST - /docente/{}/temas/registar", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            try {
                logger.debug("Tema: {}, Mestrados: {}, REM: {}", tema.getTitulo(), mestradoIds.get(0),tema.getRemuneracaoMensal());
                temaService.addTema(tema.getTitulo(), tema.getDescricao(), mestradoIds, tema.getRemuneracaoMensal());
            } catch (ApplicationException e) {
                model.addAttribute("error", e.getMessage());
                return "docente_temas_registar";
            }

            return "redirect:/docente/" + d.get().getId() + "/temas";

        } else {
            return d.isPresent() ? "error/401" : "error/404";

        }
    }
    
    @PostMapping("/docente/{id}/marcar/propostatese")
    public String marcarPropostaTese(final Model model, @PathVariable Long id, @ModelAttribute DefesaPropostaTeseDTO propostaTeseDTO) {
        logger.debug("POST - /docente/{}/marcar/propostatese", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            try {
                // Assuming you have access to the required variables
                // Replace "nota", "modalidadeApresentacao", "aluno", "juri", "propostaTese" with actual variables
                logger.debug("Nota: {}, ModalidadeApresentacao: {}, Aluno: {}, Juri: {}, PropostaTese: {}", 
                             propostaTeseDTO.getNota(), 
                             propostaTeseDTO.getModalidadeApresentacao(), 
                             propostaTeseDTO.getAluno(), 
                             propostaTeseDTO.getElementoJuri(), 
                             propostaTeseDTO.getPropostaTese());
                
                // Assuming this is the correct service method call and parameters
                candPropostaTeseService.addDefesa(propostaTeseDTO.getNota(), 
                                                  propostaTeseDTO.getModalidadeApresentacao(), 
                                                  propostaTeseDTO.getAluno(), 
                                                  propostaTeseDTO.getElementoJuri(), 
                                                  propostaTeseDTO.getPropostaTese());
            } catch (ApplicationException e) {
                model.addAttribute("error", e.getMessage());
                return "docente_marcar_propostatese";
            }
            
            return "docente_main_view";
        } else {
        	return d.isPresent() ? "error/401" : "error/404";
        }
    }

    @GetMapping("/docente/{id}/propostatese/")
    public String propostaTeseView(final Model model, @PathVariable Long id, @ModelAttribute DefesaPropostaTeseDTO propostaTeseDTO) {
        logger.debug("GET - /docente/{}/propostatese", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            model.addAttribute("propostatese", new DefesaPropostaTeseDTO());
            return "docente_propostatese";
        } else {
            return d.isPresent() ? "error/401" : "error/404";
        }

    }

    @PostMapping("/docente/{id}/propostatese/")
    public String setNotaTese(final Model model, @PathVariable Long id, @ModelAttribute Long DefesaId, @ModelAttribute Double Nota) {
        logger.debug("POST - /docente/{}/propostatese", id);
        Optional<DocenteDTO> d = docenteService.getDocente(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("docente", d.get());
            try {
                // Assuming you have access to the required variables
                // Replace "DefesaId" and "Nota" with actual variables
                logger.debug("DefesaId: {}, Nota: {}", DefesaId, Nota);

                // Assuming this is the correct service method call and parameters
                candPropostaTeseService.setNota(DefesaId, Nota);
            } catch (ApplicationException e) {
                model.addAttribute("error", e.getMessage());
                return "docente_propostatese";
            }

            return "docente_main_view";
        } else {
        	return d.isPresent() ? "error/401" : "error/404";
        }
    }

}
