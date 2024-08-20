package pt.ul.fc.css.thesisman.handlers;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.*;
import pt.ul.fc.css.thesisman.repositories.*;

@Component
public class LoginRegistosHandler extends Handler{

    Logger logger = LoggerFactory.getLogger(LoginRegistosHandler.class);

	@Autowired
    private AlunoRepositorio alunoRepositorio;
	@Autowired
    private EmpresaRepositorio empresaRepositorio;
	@Autowired
    private UtilizadorEmpresarialRepositorio utilizadorEmpresarialRepositorio;
    @Autowired
    private UtilizadorRepositorio utilizadorRepositorio;
    @Autowired
    private DocenteRepositorio docenteRepositorio;
    @Autowired
    private AdministradorRepositorio administradorRepositorio;


/////////////////////////////ESTUDANTES/////////////////////////////

    @Transactional
    public boolean loginEstudantes(String username, String pass) {

        Aluno aluno;
        List<Aluno> alunos = alunoRepositorio.findByNameAndLock(username);

        if (!alunos.isEmpty()) {
            aluno = alunos.get(0);
            if (aluno.verificaUtilizador(username, pass) && !aluno.getLogin()) {
                aluno.setLogin(true);
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public Aluno registaAluno(String username, String password)
            throws EmptyFieldException {
        if(username.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir um username");
        }

        if(password.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir uma password");
        }

        Aluno a = new Aluno(username,password);

        return alunoRepositorio.save(a);
    }

 /////////////////////////////////DOCENTES/////////////////////////////////////////

    @Transactional
    public boolean loginDocente(String username, String pass)
            throws EmptyFieldException {

        if (username.isEmpty()) {
            throw new EmptyFieldException("Nome não pode ser vazio.");
        }
        if (pass.isEmpty()) {
            throw new EmptyFieldException("Password nao pode ser vazia");
        }

        Docente docente;
        List<Docente> docentes = docenteRepositorio.findByNameAndLock(username);

        if (!docentes.isEmpty()) {
            docente = docentes.get(0);
            logger.debug("Found {}", docente);
            if (docente.verificaUtilizador(username, pass) && !docente.getLogin()) {
                docente.setLogin(true);
                docenteRepositorio.save(docente);
                logger.debug("after saving: {}", docenteRepositorio.findByName(username));
                return true;

            }
        } else {
            return false;
        }

        return false;
    }



    public Docente registaDocente(String username, String password)
            throws EmptyFieldException {
        if(username.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir um username");
        }

        if(password.isEmpty()) {
            throw new EmptyFieldException("É necessário inserir uma password");
        }

        Docente d = new Docente(username,password);

        return docenteRepositorio.save(d);
    }


/////////////////////////////UTILIZADORES EMPRESARIAIS/////////////////////////////
    @Transactional
    public UtilizadorEmpresarial registaUtilizadorEmpresarial(String username, String password, String nomeEmpresa)
            throws  EmptyFieldException, AlreadyExistsException {

    		if(username.isEmpty()) {
    			  throw new EmptyFieldException("É necessário inserir um username");
    		}
    		
    		if(password.isEmpty()) {
    			  throw new EmptyFieldException("É necessário inserir uma password");
    		}
    		
    		if(nomeEmpresa.isEmpty()) {
    			 throw new EmptyFieldException("É necessário inserir o nome de uma empresa");
    		}



    		if(!utilizadorEmpresarialRepositorio.findByName(username).isEmpty()) {
    		     throw new AlreadyExistsException("Username já existe");
    		}

            Empresa empresa;
            try {
                 empresa = empresaRepositorio.findByName(nomeEmpresa).get(0);
            } catch (IndexOutOfBoundsException e) {
                empresa = new Empresa();
                empresa.setNome(nomeEmpresa);
                empresaRepositorio.save(empresa);
            }

            UtilizadorEmpresarial utilizadorEmpresarial = new UtilizadorEmpresarial();
    		utilizadorEmpresarial.setUsername(username);
    		utilizadorEmpresarial.setPassword(password);
    		utilizadorEmpresarial.setEmpresa(empresa);

    	    return utilizadorEmpresarialRepositorio.save(utilizadorEmpresarial);
        }
    
    @Transactional
    public boolean loginUtilizadorEmpresarial(String username, String pass) {

        UtilizadorEmpresarial utilizadorEmpresarial;
        try {
            utilizadorEmpresarial = utilizadorEmpresarialRepositorio.findByNameAndLock(username).get(0);
        } catch (IndexOutOfBoundsException e) {
       	 logger.error("Este utilizador empresarial não está registado");
            return false;
        }
        
        if (utilizadorEmpresarial.verificaUtilizador(username, pass) && !utilizadorEmpresarial.getLogin()) {
                utilizadorEmpresarial.setLogin(true);
                utilizadorEmpresarialRepositorio.save(utilizadorEmpresarial);
                logger.info("{} - Autenticado com sucesso", utilizadorEmpresarial.getUsername());
                return true;
        }
        return false;
    }    


////////////////////////ADMINISTRADORES/////////////////////////

    @Transactional
    public boolean loginAdministrador(String username, String pass) {

        Administrador admin;
        try {
            admin = administradorRepositorio.findByNameAndLock(username).get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Este Administrador não está registado");
            return false;
        }

        if (admin.verificaUtilizador(username, pass) && !admin.getLogin()) {
            admin.setLogin(true);
            administradorRepositorio.save(admin);
            logger.info("{} - Autenticado com sucesso", admin.getUsername());
            return true;
        }
        return false;
    }


/////////////////////////////GERAL/////////////////////////////
    
    public boolean logout(String username) {
        List<Utilizador> users =  utilizadorRepositorio.findByName(username);
        if (users.isEmpty()) {
            logger.error("{} - nao encontrado", username);
            return false;
        }
        Utilizador user =users.get(0);
        if (user != null) {
            logger.info("{} - De-autenticado com sucesso", user.getUsername());
            user.setLogin(false);
            return true;
        }
        logger.error("{} - nao encontrado", username);
        return false;
    }
}
