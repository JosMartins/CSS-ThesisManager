package pt.ul.fc.css.thesisman.handlers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.*;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;
import pt.ul.fc.css.thesisman.repositories.CandidaturaTeseRepositorio;
import pt.ul.fc.css.thesisman.repositories.TemaRepositorio;

@Component
public class CandidaturaHandler extends Handler {

    @Autowired
    private TemaRepositorio catalogoTemas;

    @Autowired
    private CandidaturaTeseRepositorio catalogoCandidaturas;

    @Autowired
    private AlunoRepositorio alunoRep;


    public CandidaturaTese submeterCandidatura(List<Tema> temas, AlunoDTO alunoDTO) throws EmptyFieldException { //Caso G

        Aluno aluno = alunoRep.findById(alunoDTO.getId()).orElseThrow(() -> new EmptyFieldException("Aluno não encontrado"));

        for (CandidaturaTese candidatura : catalogoCandidaturas.findAll()) {

            if (candidatura.getAluno().equals(aluno)) {

                System.err.println("O aluno ja tem uma candidatura");
                return null;

            }
        }
        CandidaturaTese candTese = new CandidaturaTese(aluno, temas);

        return catalogoCandidaturas.save(candTese);

    }


    public boolean cancelarCandidatura(Long id, CandidaturaTese candidaturaAtualizada) throws EmptyFieldException {
        CandidaturaTese cand = catalogoCandidaturas.findById(id).orElseThrow(() -> new EmptyFieldException("Candidatura não encontrada"));

        if (candidaturaAtualizada.getListaTemas().isEmpty()) {
            throw new EmptyFieldException("É necessário no minimo um tema para candidatura");
        }

        if (Objects.equals(cand.getId(), candidaturaAtualizada.getId()) &&
                cand.getAluno().equals(candidaturaAtualizada.getAluno())) {
            catalogoCandidaturas.save(candidaturaAtualizada);
        }

        return true;

    }
}
