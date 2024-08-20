package pt.ul.fc.css.thesisman.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.UtilizadorEmpresarialDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.thesisman.handlers.LoginRegistosHandler;
import pt.ul.fc.css.thesisman.repositories.UtilizadorEmpresarialRepositorio;

@Component
public class UtilizadorEmpresarialService {

	@Autowired
	private UtilizadorEmpresarialRepositorio repositorioUtilizadoresEmpresariais;

	@Autowired
	private LoginRegistosHandler registoLoginUtilizadorEmpresarialHandler;
    @Autowired
    private UtilizadorEmpresarialRepositorio utilizadorEmpresarialRepositorio;

	public UtilizadorEmpresarialDTO registaUtilizadorEmpresarial(String username, String password, String nomeEmpresa) throws EmptyFieldException, AlreadyExistsException {

		UtilizadorEmpresarial u = registoLoginUtilizadorEmpresarialHandler.registaUtilizadorEmpresarial(username, password, nomeEmpresa);
		return UtilizadorEmpresarialService.dtofy(u);

	}
	
	
	public Optional<UtilizadorEmpresarialDTO> loginUtilizadorEmpresarial(String username, String pass) {


		 if (registoLoginUtilizadorEmpresarialHandler.loginUtilizadorEmpresarial(username, pass)) {
			 return Optional.of(dtofy(utilizadorEmpresarialRepositorio.findByName(username).get(0)));
		 } else {
			 return Optional.empty();
		 }
    }
	
	


	public List<UtilizadorEmpresarialDTO> getUtilEmp() {
		ArrayList<UtilizadorEmpresarialDTO> arr = new ArrayList<>();
		for (UtilizadorEmpresarial utiEmp : repositorioUtilizadoresEmpresariais.findAll()) {
			UtilizadorEmpresarialDTO dto = dtofy(utiEmp);
			arr.add(dto);
		}
		return arr;

	}

	public Optional<UtilizadorEmpresarialDTO> getUtilEmp(Long id) {
		return repositorioUtilizadoresEmpresariais.findById(id).map(UtilizadorEmpresarialService::dtofy);
	}


	private static UtilizadorEmpresarialDTO dtofy(UtilizadorEmpresarial u) {

		UtilizadorEmpresarialDTO uDTO = new UtilizadorEmpresarialDTO();
		uDTO.setId(u.getId());
		uDTO.setUsername(u.getUsername());
		uDTO.setPassword(u.getPassword());
		uDTO.setLogin(u.getLogin());
		uDTO.setNomeEmpresa(u.getEmpresa().getNome());
		return uDTO;
	}

}
