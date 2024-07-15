package pt.ul.fc.css.thesisman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.services.MestradoDTO;
import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.Tema;
import pt.ul.fc.css.thesisman.handlers.TemasTesesHandler;
import pt.ul.fc.css.thesisman.repositories.TemaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TemaService {

    @Autowired
    private TemaRepositorio temaRep;

    @Autowired
    private TemasTesesHandler temasTesesHandler;
    @Autowired
    private MestradoService mestradoService;


    public List<TemaDTO> getTemas() {
        ArrayList<TemaDTO> arr = new ArrayList<>();
        for (Tema tema : temaRep.findAll()) {
            TemaDTO dto = dtofy(tema);
            arr.add(dto);
        }
        return arr;
    }

    public Optional<TemaDTO> getTema(Long id) {
        return temaRep.findById(id).map(TemaService::dtofy);
    }

    public Optional<TemaDTO> getTema(String name) {
        List<Tema> tema = temaRep.findByName(name);

        if (tema.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(TemaService.dtofy(tema.get(0)));
        }

    }


    @Transactional
    public Optional<TemaDTO> addTema(String nome, String descricao, List<Long> mestradoIds, double remuneracaoMensal)
            throws EmptyFieldException {

        if (nome.isEmpty()) {
            throw new EmptyFieldException("Nome não pode ser vazio");
        }

        if (descricao.isEmpty()) {
            throw new EmptyFieldException("Descrição não pode ser vazia");
        }

        if (descricao.length() > 512) {
            throw new EmptyFieldException("Descrição não pode ter mais de 512 caracteres");
        }

        if (!temaRep.findByName(nome).isEmpty()) {
            return Optional.empty();
        }

        List<MestradoDTO> mestrados = mestradoService.getMestrados().stream().filter(mestrado -> mestradoIds.contains(mestrado.getId())).toList();


        return Optional.of(dtofy(temasTesesHandler.submitTema(nome, descricao, mestrados, remuneracaoMensal)));
    }

    static TemaDTO dtofy(Tema tema) {
        TemaDTO dto = new TemaDTO();
        dto.setId(tema.getId());
        dto.setMestradosCompativeis(tema.getMestradosCompativeis().stream().map(MestradoService::dtofy).toList());
        dto.setDescricao(tema.getDescricao());
        dto.setRemuneracaoMensal(tema.getRemuneracaoMensal());
        dto.setTitulo(tema.getTitulo());

        return dto;
    }

}