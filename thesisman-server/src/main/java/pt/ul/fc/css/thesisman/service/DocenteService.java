package pt.ul.fc.css.thesisman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.business.services.DocenteDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.Docente;
import pt.ul.fc.css.thesisman.handlers.LoginRegistosHandler;
import pt.ul.fc.css.thesisman.repositories.DocenteRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DocenteService {

    Logger logger = LoggerFactory.getLogger(DocenteService.class);
    @Autowired
    private DocenteRepositorio docenteRep;

    @Autowired
    private LoginRegistosHandler loginRegistosHandler;

    public List<DocenteDTO> getDocentes() {
        ArrayList<DocenteDTO> arr = new ArrayList<>();
        for (Docente docente : docenteRep.findAll()) {
            DocenteDTO dto = dtofy(docente);
            arr.add(dto);
        }
    return arr;
    }

    public Optional<DocenteDTO> getDocente(Long id) {
        return docenteRep.findById(id).map(DocenteService::dtofy);
    }

    public  DocenteDTO registerDocente(String username, String password)
            throws EmptyFieldException {
        Docente d = loginRegistosHandler.registaDocente(username, password);

        return DocenteService.dtofy(d);
    }

    public Optional<DocenteDTO> login(String name, String pass)
            throws EmptyFieldException {

        if(loginRegistosHandler.loginDocente(name,pass)) {
            return Optional.of(dtofy(docenteRep.findByName(name).get(0)));
        } else {
            return Optional.empty();
        }

    }


    static DocenteDTO dtofy(Docente docente) {
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setUsername(docente.getUsername());
        dto.setOccupacao(docente.getTimeSlots());
        dto.setLogin(docente.getLogin());

        return dto;
    }
}