package pt.ul.fc.css.thesisman.business.services;

import javax.swing.text.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.entities.Aluno;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoDTO {
	
	private Long id;
	private String username;
	private String password;
	private Boolean login;
	private MestradoDTO mestrado;
	private Double media;
	private String propostaTese;
	private String tese;
	
	
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
	
	public void setPassword(String novoPassword) {
		this.password = novoPassword;
	}
	
	public Boolean isLogin() {
		return login;
	}
	
	public void setLogin(boolean novoLogin) {
		this.login = novoLogin;
	}
	
	public MestradoDTO getMestrado() {
		return mestrado;
	}
	
	public void setMestrado(MestradoDTO novoMestrado) {
		this.mestrado = novoMestrado;
	}
	
	public Double getMedia() {
		return media;
	}
	
	public void setMedia(double novaMedia) {
		this.media = novaMedia;
	}

	public String getPropostaTese() {
		return propostaTese;
	}

	public void setPropostaTese(String propostaTese) {
		this.propostaTese = propostaTese;
	}

	public String getTese() {
		return tese;
	}

	public void setTese(String tese) {
		this.tese = tese;
	}

}
