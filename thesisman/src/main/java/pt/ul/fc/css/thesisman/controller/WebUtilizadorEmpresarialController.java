package pt.ul.fc.css.thesisman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.ApplicationException;
import pt.ul.fc.css.thesisman.business.services.UtilizadorEmpresarialDTO;
import pt.ul.fc.css.thesisman.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.thesisman.service.TemaService;
import pt.ul.fc.css.thesisman.service.UtilizadorEmpresarialService;

import java.util.Optional;


@Controller
public class WebUtilizadorEmpresarialController {

    Logger logger = LoggerFactory.getLogger(WebUtilizadorEmpresarialController.class);

    @Autowired
    private UtilizadorEmpresarialService uEmpService;

    @Autowired
    private UtilizadorEmpresarialService utilizadorEmpresarialService;

    @Autowired
    private TemaService temaService;


    @GetMapping("/utilizador-empresarial/{id}")
    public String uEmpDetails(final Model model, @PathVariable Long id) {
        logger.info("GET - utilizador-empresarial/{}", id);
        Optional<UtilizadorEmpresarialDTO> uE = uEmpService.getUtilEmp(id);
        if (uE.isPresent()) {
            model.addAttribute("uE", uE);
            return "utilizador-empresarial_main_view";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/utilizadores-empresariais/registar")
    public String newUtilizadorEmpresarial(final Model model) {
        logger.info("GET - utilizadores-empresariais/registar");
        model.addAttribute("uE", new UtilizadorEmpresarial());
        return "utilizador-empresarial_registar";
    }

    @PostMapping("/utilizadores-empresariais/registar")
    public String newUtilizadorEmpresarialAction(final Model model, @ModelAttribute UtilizadorEmpresarialDTO uE) {
        logger.info("POST - utilizadores-empresariais/registar");
        UtilizadorEmpresarialDTO u2;
        try {
            uEmpService.registaUtilizadorEmpresarial(uE.getUsername(), uE.getPassword(), uE.getNomeEmpresa());
            logger.debug("UtilizadorEmpresarial adicionado à base de dados.");
            return "redirect:/";
        } catch (ApplicationException e) {
            u2 = new UtilizadorEmpresarialDTO();
            model.addAttribute("uE", u2);
            model.addAttribute("error", e.getMessage());
            return "utilizador-empresarial_registar";
        }
    }


    @GetMapping("/utilizadores-empresariais/login")
    public String utilizadorEmpresarialLogin(final Model model, @RequestParam(value = "b", required = false) String b) {
        logger.debug("GET - from /utilizadorEmpresarial/login");
        if (b != null && Boolean.parseBoolean(b)) {
            model.addAttribute("wrongPass", "Password Errada.");
        }
        model.addAttribute("uE", new UtilizadorEmpresarial());
        return "utilizador-empresarial_login";
    }

    @PostMapping("/utilizadores-empresariais/login")
    public String loginUtilizadorEmpresarialAction(final Model model, @ModelAttribute UtilizadorEmpresarialDTO uE) {
        logger.info("POST - /utilizadores-empresariais/login");
        Optional<UtilizadorEmpresarialDTO> uE2 = utilizadorEmpresarialService.loginUtilizadorEmpresarial(uE.getUsername(), uE.getPassword());
        if (uE2.isPresent()) {
            return "redirect:/utilizador-empresarial/" + uE2.get().getId() + "/main";
        } else {
            return "redirect:/utilizadores-empresariais/login?b=true";
        }

    }

    @GetMapping("/utilizador-empresarial/{id}/main")
    public String mainUtilizadorEmpresarial(final Model model, @PathVariable Long id) {
        logger.info("GET - /utilizador-empresarial/{}/main", id);
        Optional<UtilizadorEmpresarialDTO> uE = uEmpService.getUtilEmp(id);
        if (uE.isPresent() && uE.get().getLogin()) {
            model.addAttribute("uE", uE.get());
            return "utilizador-empresarial_main";
        } else {
            return uE.isPresent() ? "error/401" : "error/404";
        }
    }

    @GetMapping("/utilizador-empresarial/{id}/temas")
    public String listTemas(final Model model, @PathVariable Long id) {
        logger.info("GET - /utilizador-empresarial/{}/temas", id);
        Optional<UtilizadorEmpresarialDTO> uE = uEmpService.getUtilEmp(id);

        if (uE.isPresent() && uE.get().getLogin()) {
            model.addAttribute("uE", uE.get());
            model.addAttribute("temas", temaService.getTemas());
            return "utilizador-empresarial_temas";
        } else {
            return  uE.isPresent() ? "error/401" : "error/404";
        }
    }

    @GetMapping("/utilizador-empresarial/{id}/temas/registar")
    public String submeterTema(final Model model, @PathVariable Long id) {
        logger.debug("GET - from /utilizador-empresarial/{}/temas/submeter", id);
        Optional<UtilizadorEmpresarialDTO> uE = utilizadorEmpresarialService.getUtilEmp(id);
        if (uE.isPresent() && uE.get().getLogin() ) {
            model.addAttribute("uE", uE.get());
            model.addAttribute("tema", new TemaDTO());
            return "utilizador-empresarial_temas_registar";
        } else {
            return  uE.isPresent() ? "error/401" : "error/404";
        }
    }

    @PostMapping("/utilizador-empresarial/{id}/temas/registar")
    public String submeterTema(final Model model, @PathVariable Long id, @ModelAttribute TemaDTO tema) {
        logger.debug("POST - /utilizador-empresarial/{}/temas/submeter", id);
        Optional<UtilizadorEmpresarialDTO> uE = utilizadorEmpresarialService.getUtilEmp(id);
        if (uE.isPresent() && uE.get().getLogin()) {
            model.addAttribute("uE", uE.get());
            try {
                temaService.addTema(tema.getTitulo(), tema.getDescricao(), null, tema.getRemuneracaoMensal());
            } catch (ApplicationException e) {
                model.addAttribute("error", e.getMessage());
                return "utilizador-empresarial_temas_registar";

            }

            return  "redirect:/utilizador-empresarial/" + uE.get().getId() + "/temas";

            } else {
                return uE.isPresent() ? "error/401" : "error/404";

            }
    }
}
