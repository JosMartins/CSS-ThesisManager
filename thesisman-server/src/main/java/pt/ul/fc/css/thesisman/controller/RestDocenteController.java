package pt.ul.fc.css.thesisman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ul.fc.css.thesisman.business.services.DocenteDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.service.DocenteService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api")
public class RestDocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/docentes")
    List<DocenteDTO> all() {
        return docenteService.getDocentes();
    }

    @GetMapping("/docente/{id}")
    Optional<DocenteDTO> get(@PathVariable Long id) {
        return docenteService.getDocente(id);
    }

    @PostMapping("/docentes/registar")
    ResponseEntity<DocenteDTO> createDocente(@RequestBody DocenteDTO newDocente) { //TODO stor tem ResponseEntity<?>, maybe é esse q é preciso..
        try {
            DocenteDTO d = docenteService.registerDocente(newDocente.getUsername(), newDocente.getPassword());
            return ResponseEntity.ok().body(d);
        } catch (EmptyFieldException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
