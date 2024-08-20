package pt.ul.fc.css.thesisman.handlers;


import pt.ul.fc.css.thesisman.entities.Aluno;
import pt.ul.fc.css.thesisman.entities.DefesaPropostaTese;
import pt.ul.fc.css.thesisman.entities.Docente;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;
import pt.ul.fc.css.thesisman.entities.Tese;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;
import pt.ul.fc.css.thesisman.repositories.TeseRepositorio;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissaoDefesaDePropostaHandler extends Handler {
	
	Logger logger = LoggerFactory.getLogger(SubmissaoDefesaDePropostaHandler.class);
	
	@Autowired
	private TeseRepositorio teseRepositorio;
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;
	    
	public SubmissaoDefesaDePropostaHandler (TeseRepositorio teseRep, AlunoRepositorio alunoRep) {

		teseRepositorio = teseRep;
		alunoRepositorio = alunoRep;

	}
	
//TODO - provavelmente vai ter de receber horaInicio 
//TODO - ver se faz sentido ser boolean
    public boolean marcarDefesaDeProposta(Tese tese, ModalidadeApresentacao modalidadeApresentacao , Docente docenteJuri) throws EmptyFieldException {
    	
    	if(tese == null) {
    		throw new EmptyFieldException("É necessário inserir uma tese");
    	}
    	
    	
    	if(modalidadeApresentacao == null) {
    		throw new EmptyFieldException("É necessário inserir uma modalidade de apresentação");
    	}
    	
    	if(docenteJuri == null) {
    		throw new EmptyFieldException("É necessário inserir um docente juri");
    	}
    	

    	
    	DefesaPropostaTese defesaPropostaTese = new DefesaPropostaTese();
    	//TODO - horaInicio
    	defesaPropostaTese.setModalidadeApresentacao(modalidadeApresentacao);
    	//TODO - Caso da sala
    	defesaPropostaTese.setElementoJuri(docenteJuri);
    	
    	tese.addDefesaPropostaTese(defesaPropostaTese);
        return true;
    }

	public boolean submitTese(Aluno aluno) {
		if(alunoRepositorio.existsById(aluno.getId())) {
			alunoRepositorio.save(aluno);// atualiza o aluno com o atributo proposta de tese
			return true;
		}
		return false;
	}

}
