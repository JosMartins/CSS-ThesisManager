package pt.ul.fc.css.thesisman.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.entities.Aluno;
import pt.ul.fc.css.thesisman.entities.Tese;
import pt.ul.fc.css.thesisman.repositories.TeseRepositorio;

@Component
public class SubmissaoDefesaDeTeseHandler extends Handler{
	
	@Autowired
	private TeseRepositorio teseRep;

	public SubmissaoDefesaDeTeseHandler (TeseRepositorio teseRepositorio) {

		teseRep = teseRepositorio;

	}
	
	public boolean submitTese (Aluno aluno) {
		
		for (Tese repTese : teseRep.findAll()) {
			if(repTese.getAluno().getId() == aluno.getId()) {
				return true;
			}
		}
		return false;
		
	}

}
