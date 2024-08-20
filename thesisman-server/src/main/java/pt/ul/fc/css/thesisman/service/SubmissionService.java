package pt.ul.fc.css.thesisman.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.entities.Aluno;
import pt.ul.fc.css.thesisman.handlers.SubmissaoDefesaDePropostaHandler;
import pt.ul.fc.css.thesisman.handlers.SubmissaoDefesaDeTeseHandler;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;
import pt.ul.fc.css.thesisman.repositories.TeseRepositorio;

import java.util.Optional;

@Component
public class SubmissionService {

	@Autowired
	private TeseRepositorio teseRep;
	
    @Autowired
    private AlunoRepositorio alunoRep;

	@Autowired
	private SubmissaoDefesaDeTeseHandler subTeseHand;

	@Autowired
	private SubmissaoDefesaDePropostaHandler subPropostaHand;


	public boolean submitTese (AlunoDTO alunodto) {

        Optional<Aluno> aluno = alunoRep.findById(alunodto.getId());

		return aluno.isPresent() && subTeseHand.submitTese(aluno.get());
	}

	public boolean submitProposta(AlunoDTO alunodto) {

		Optional<Aluno> a = alunoRep.findById(alunodto.getId());

		return a.isPresent() && subPropostaHand.submitTese(a.get());
	}

}
