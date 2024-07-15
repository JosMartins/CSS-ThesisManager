package pt.ul.fc.css.thesisman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.EmpresaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.Empresa;
import pt.ul.fc.css.thesisman.repositories.EmpresaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmpresaService {

    Logger log = LoggerFactory.getLogger(EmpresaService.class);

    @Autowired
    private EmpresaRepositorio empresaRep;

    public EmpresaDTO criarEmpresa(String nome, String endereco) throws EmptyFieldException, AlreadyExistsException {

        return null;

    }


    public List<EmpresaDTO> getEmpresa() {
        ArrayList<EmpresaDTO> arr = new ArrayList<>();
        for (Empresa e : empresaRep.findAll()) {
            EmpresaDTO eDTO = dtfy(e);
            arr.add(eDTO);
        }
        return arr;

    }

    public Optional<EmpresaDTO> getEmpresa(Long id) {
        return empresaRep.findById(id).map(EmpresaService::dtfy);
    }


    private static EmpresaDTO dtfy(Empresa e) {

        EmpresaDTO eDTO = new EmpresaDTO();
        eDTO.setId(e.getId());
        eDTO.setNome(e.getNome());
        eDTO.setEndereco(e.getEndereco());

        return eDTO;
    }
}
