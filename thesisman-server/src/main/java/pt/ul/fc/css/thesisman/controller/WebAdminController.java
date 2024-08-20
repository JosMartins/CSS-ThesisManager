package pt.ul.fc.css.thesisman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pt.ul.fc.css.thesisman.business.services.*;
import pt.ul.fc.css.thesisman.business.services.exceptions.ApplicationException;
import pt.ul.fc.css.thesisman.entities.Administrador;
import pt.ul.fc.css.thesisman.service.AdministradorService;
import pt.ul.fc.css.thesisman.service.CandidaturaTeseService;
import pt.ul.fc.css.thesisman.service.DocenteService;
import pt.ul.fc.css.thesisman.service.UtilizadorEmpresarialService;

import java.util.Optional;

@Controller
public class WebAdminController {

    Logger logger = LoggerFactory.getLogger(WebAdminController.class);

    @Autowired
    private AdministradorService adminService;

    @Autowired
    private UtilizadorEmpresarialService uEmpService;

    @Autowired
    private DocenteService docenteService;
    @Autowired
    private CandidaturaTeseService candidaturaTeseService;


    @GetMapping("/admin/login")
    public String adminLogin(final Model model, @RequestParam(value = "b", required = false) String b) {
        logger.debug("GET - from /admin/login");
        if (b != null && Boolean.parseBoolean(b)) {
            model.addAttribute("wrongPass", "Password Errada.");
        }
        model.addAttribute("admin", new Administrador());
        return "admin_login";
    }

    @PostMapping("/admin/login")
    public String adminLoginAction(final Model model, @ModelAttribute AdministradorDTO d) {
        logger.debug("POST - from /admin/login");
        try {
            Optional<AdministradorDTO> d2 = adminService.login(d.getUsername(), d.getPassword());
            logger.debug(d2.get().getId() + "");
            return d2.map(docenteDTO -> "redirect:/admin/" + d2.get().getId() + "/main").orElse("redirect:/admin/login?b=true");

        } catch (ApplicationException e) {
            model.addAttribute("admin", new AdministradorDTO());
            model.addAttribute("error", e.getMessage());
            return "admin_login";

        }
    }

    @GetMapping("/admin/{id}/main")
    public String adminMain(final Model model, @PathVariable Long id) {
        logger.debug("GET - from /docente/{}/main", id);
        Optional<AdministradorDTO> d = adminService.getAdministrador(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("admin", d.get());
            return "admin_main";
        } else {
            return d.isPresent() ? "error/401" :"error/404";
        }
    }

    @GetMapping("/admin/{id}/candidaturas")
    public String submeterCandidaturaMan(final Model model, @PathVariable Long id) {
        logger.debug("GET - /admin/{}/candidaturas", id);
        Optional<AdministradorDTO> d = adminService.getAdministrador(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("admin", d.get());
            model.addAttribute("candidaturas", d.get().getCandidaturasManuais());
            return "admin_candidaturas";
        } else {
            return d.isPresent() ? "error/401" : "error/404";
        }
    }

    @PostMapping("/admin/{id}/candidaturas")
    public String submeterCandidaturaManAction(final Model model, @PathVariable Long id, @RequestParam("candidaturaId") Long candidaturaId, @RequestParam("selectedTema") Long temaId) {
        logger.debug("POST - /admin/{}/candidaturas", id);
        Optional<AdministradorDTO> d = adminService.getAdministrador(id);
        if (d.isPresent() && d.get().getLogin()) {
            model.addAttribute("admin", d.get());
            try {
                Optional<CandidaturaTeseDTO> cand = candidaturaTeseService.getCandidatura(candidaturaId);
                if (!cand.isPresent()) {
                    model.addAttribute("error", "Candidatura não encontrada");
                    return "admin_candidaturas";
                }

                candidaturaTeseService.setTemaCandidatura(cand.get(), temaId);
            } catch (ApplicationException e) {
                model.addAttribute("error", e.getMessage());
                return "admin_candidaturas";
            }

            return "redirect:/admin/" + d.get().getId() + "/candidaturas";

        } else {
            return d.isPresent() ? "error/401" : "error/404";

        }
    }




    @GetMapping("/admin/{id}/utilizadores-empresariais")
    public String utilizadoresEmp(final Model model, @PathVariable Long id) {
        logger.info("GET - /admin/{}/utilizadores-empresariais", id);
        Optional<AdministradorDTO> d = adminService.getAdministrador(id);
        if (d.isPresent()) {
            if (d.get().getLogin()) {
                model.addAttribute("admin", d.get());
                model.addAttribute("uE", uEmpService.getUtilEmp());
            } else {
                return "error/401";
            }
        }
        return "utilizadores-empresariais_list";
    }

    @GetMapping("/admin/{id}/docentes")
    public String docentes(final Model model, @PathVariable Long id) {
        logger.info("GET - /admin/{}/docentes", id);
        Optional<AdministradorDTO> d = adminService.getAdministrador(id);
        if (d.isPresent()) {
            if (d.get().getLogin()) {
                model.addAttribute("admin", d.get());
                model.addAttribute("docentes", docenteService.getDocentes());
            } else {
                return "error/401";
            }
        }
        return "docentes_list";
    }
}
