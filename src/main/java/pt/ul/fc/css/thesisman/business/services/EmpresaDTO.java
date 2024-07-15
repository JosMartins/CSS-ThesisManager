package pt.ul.fc.css.thesisman.business.services;

import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.entities.Empresa;

@Component
public class EmpresaDTO {
	
	private Long id;
	private String nome;
	private String endereco;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long novoId) {
		this.id = novoId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String novoEndereco) {
		this.endereco = novoEndereco;
	}

}
