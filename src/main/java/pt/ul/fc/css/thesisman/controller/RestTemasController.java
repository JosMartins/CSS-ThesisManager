package pt.ul.fc.css.thesisman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.service.TemaService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api")
public class RestTemasController {

    @Autowired
    private TemaService temaService;

    @GetMapping("/temas")
    List<TemaDTO> all() {
        return temaService.getTemas();
    }

    @GetMapping("/temas/{name}")
    Optional<TemaDTO> all(@PathVariable String name) {
        return temaService.getTema(name);
    }
}
