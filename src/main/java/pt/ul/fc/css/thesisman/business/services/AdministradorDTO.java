package pt.ul.fc.css.thesisman.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.entities.Administrador;
import pt.ul.fc.css.thesisman.entities.CandidaturaTese;

@Component
public class AdministradorDTO {
	
	private Long id;
	private String username;
	private String password;
	private boolean login;
	private List<CandidaturaTeseDTO> candidaturasManuaisIds;
	
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String novaPassword) {
		this.password = novaPassword;
	}
	
	public boolean getLogin() {
		return login;
	}
	
	public void setLogin(boolean novoLogin) {
		this.login = novoLogin;
	}
	
	public List<CandidaturaTeseDTO> getCandidaturasManuais() {
		return candidaturasManuaisIds;
	}
	
	public void setCandidaturasManuais(List<CandidaturaTeseDTO> novasCandidaturasManuaisIds) {
		this.candidaturasManuaisIds = novasCandidaturasManuaisIds;
	}

}
