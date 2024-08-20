package pt.ul.fc.css.thesisman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.MestradoDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.Aluno;
import pt.ul.fc.css.thesisman.handlers.LoginRegistosHandler;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlunoService {

    @Autowired
    private AlunoRepositorio alunoRep;

    @Autowired
    private LoginRegistosHandler loginRegistosHandler;


    public List<AlunoDTO> getAlunos() {
        ArrayList<AlunoDTO> arr = new ArrayList<>();
        for (Aluno aluno : alunoRep.findAll()) {
            AlunoDTO dto = dtofy(aluno);
            arr.add(dto);
        }
        return arr;
    }

    public Optional<AlunoDTO> getAluno(Long id) {
        return alunoRep.findById(id).map(AlunoService::dtofy);
    }


    public Optional<AlunoDTO> login(String username, String password)
            throws EmptyFieldException {
        if (username.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir um username");
        }

        if (password.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir uma password");
        }

        if (loginRegistosHandler.loginEstudantes(username, password)) {
            List<Aluno> alunos = alunoRep.findByName(username);

            if (!alunos.isEmpty()) {
                return Optional.of(dtofy(alunoRep.findByName(username).get(0)));
            }
        }

        return Optional.empty();

    }

    public Optional<AlunoDTO> logout(AlunoDTO aluno) throws EmptyFieldException {

        if (aluno.getUsername().isEmpty()) {
            throw new EmptyFieldException("É necessário inserir um username");
        }

        if (aluno.getPassword().isEmpty()) {
            throw new EmptyFieldException("É necessário inserir uma password");
        }

        if (!alunoRep.findByName(aluno.getUsername()).get(0).getPassword().equals(aluno.getPassword())) {
            return Optional.empty();
        }

        if (loginRegistosHandler.logout(aluno.getUsername())) {
            return Optional.of(dtofy(alunoRep.findByName(aluno.getPassword()).get(0)));
        }
        return Optional.empty();
    }


    static AlunoDTO dtofy(Aluno aluno) {

        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setUsername(aluno.getUsername());
        dto.setPassword(aluno.getPassword());
        dto.setMedia(aluno.getMedia());
        dto.setLogin(aluno.getLogin());

        if (aluno.getMestrado() != null) {
            MestradoDTO mestrado = new MestradoDTO();
            mestrado.setId(aluno.getMestrado().getId());
            mestrado.setNome(aluno.getMestrado().getNome());
            mestrado.setAlunosInscritos(aluno.getMestrado().getAlunosInscritos().stream().map(AlunoService::dtofy).toList());
            dto.setMestrado(mestrado);
        }

        return dto;
    }

    public void logout(String username) {

        List<Aluno> als = alunoRep.findByName(username);
        if (!als.isEmpty()) {
            loginRegistosHandler.logout(username);
            alunoRep.save(als.get(0));
        }

    }

}