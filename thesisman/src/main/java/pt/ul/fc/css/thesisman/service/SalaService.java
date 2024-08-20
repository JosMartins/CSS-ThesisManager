package pt.ul.fc.css.thesisman.service;

import pt.ul.fc.css.thesisman.business.services.SalaDTO;
import pt.ul.fc.css.thesisman.entities.Sala;

public class SalaService {



    public static SalaDTO dtofy(Sala sala) {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(sala.getId());
        salaDTO.setDesignacao(sala.getDesignacao());
        salaDTO.setOcupacao(sala.getOcupacao());
        return salaDTO;
    }
}
