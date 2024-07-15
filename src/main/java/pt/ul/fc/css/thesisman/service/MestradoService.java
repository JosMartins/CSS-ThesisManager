package pt.ul.fc.css.thesisman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.MestradoDTO;
import pt.ul.fc.css.thesisman.entities.Mestrado;
import pt.ul.fc.css.thesisman.repositories.MestradoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MestradoService {

    @Autowired
    private MestradoRepositorio mestradoRep;


    public List<MestradoDTO> getMestrados() {
        ArrayList<MestradoDTO> arr = new ArrayList<>();
        for (Mestrado mes : mestradoRep.findAll()) {
            MestradoDTO dto = dtofy(mes);
            arr.add(dto);
        }
        return arr;
    }

    public Optional<MestradoDTO> getMestrado(Long id) {
        return mestradoRep.findById(id).map(MestradoService::dtofy);
    }

    protected static MestradoDTO dtofy(Mestrado mestrado) {

        MestradoDTO dto = new MestradoDTO();
        dto.setId(mestrado.getId());
        dto.setNome(mestrado.getNome());
        dto.setAlunosInscritos(mestrado.getAlunosInscritos().stream().map(AlunoService::dtofy).toList());
        return dto;
    }
}
