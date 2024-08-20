package pt.ul.fc.css.thesisman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ul.fc.css.thesisman.business.services.DefesaPropostaTeseDTO;
import pt.ul.fc.css.thesisman.service.DefesaPropostaTeseService;

import java.util.List;

@Controller
public class WebController {

    Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    DefesaPropostaTeseService defesaPropostaTeseService;

    public WebController() {
        super();
    }

    @GetMapping("/")
    public String getStartPage(final Model model) {
        logger.debug("GET - from /index");
        return "index";
    }


    @GetMapping("/statistics")
    public String studentStatistics(final Model model) {
        logger.debug("GET - from /statistics");

        List<DefesaPropostaTeseDTO> defesas = defesaPropostaTeseService.getDefesas();
        model.addAttribute("totalDefesas", defesas.size());
        model.addAttribute("defesasPassados", defesas.stream().filter(d -> d.getNota() >= 9.5).count());
        model.addAttribute("defesasReprovados", defesas.stream().filter(d -> d.getNota() < 9.5).count());
        model.addAttribute("mediaNotas", defesas.stream().mapToDouble(DefesaPropostaTeseDTO::getNota).average().orElse(0.0));

        return "statistics";
    }
}
