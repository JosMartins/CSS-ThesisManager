package pt.ul.fc.css.thesisman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.services.AdministradorDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.Administrador;
import pt.ul.fc.css.thesisman.entities.CandidaturaTese;
import pt.ul.fc.css.thesisman.handlers.LoginRegistosHandler;
import pt.ul.fc.css.thesisman.repositories.AdministradorRepositorio;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class AdministradorService {

    Logger logger = LoggerFactory.getLogger(DocenteService.class);
    @Autowired
    private AdministradorRepositorio adminRep;

    @Autowired
    private LoginRegistosHandler loginRegistosHandler;

    public List<AdministradorDTO> getAdministradores() {
        ArrayList<AdministradorDTO> arr = new ArrayList<>();
        for (Administrador admin : adminRep.findAll()) {
            AdministradorDTO dto = dtofy(admin);
            arr.add(dto);
        }
        return arr;
    }

    public Optional<AdministradorDTO> getAdministrador(Long id) {
        return adminRep.findById(id).map(AdministradorService::dtofy);
    }


    public Optional<AdministradorDTO> login(String name, String pass)
            throws EmptyFieldException {

        if(loginRegistosHandler.loginAdministrador(name,pass)) {
            return Optional.of(dtofy(adminRep.findByName(name).get(0)));
        } else {
            return Optional.empty();
        }

    }

    public boolean adicionarCandidatura(CandidaturaTese ct) {

        List<Administrador> admins = (List<Administrador>) adminRep.findAll();
        if (admins.isEmpty()) {
            return false;
        }

        SecureRandom rand = new SecureRandom();
        int randomIndex = rand.nextInt(admins.size());
        Administrador randomAdmin = admins.get(randomIndex);

        randomAdmin.getCandidaturasManuais().add(ct);
        adminRep.save(randomAdmin);
        return true;
    }

    private static AdministradorDTO dtofy(Administrador admin) {
        AdministradorDTO dto = new AdministradorDTO();

        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setLogin(admin.getLogin());

        dto.setCandidaturasManuais(admin.getCandidaturasManuais().stream().map(CandidaturaTeseService::dtofy).toList());

        return dto;
    }
}
