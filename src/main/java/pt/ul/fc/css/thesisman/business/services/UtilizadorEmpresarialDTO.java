package pt.ul.fc.css.thesisman.business.services;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.entities.Empresa;
import pt.ul.fc.css.thesisman.entities.UtilizadorEmpresarial;

@Component
public class UtilizadorEmpresarialDTO {
	
	private Long id;
	private String username;
	private String password;
	private boolean login;
	private String nomeEmpresa;
	private EmpresaDTO empresa;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long novoId) {
		this.id = novoId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String novoUsername) {
		this.username = novoUsername;
	}
	
	public boolean getLogin() {
		return login;
	}
	
	public void setLogin(boolean novoLogin) {
		this.login = novoLogin;
	}
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	
	public void setNomeEmpresa(String newNomeEmpresa) {
		this.nomeEmpresa = newNomeEmpresa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

