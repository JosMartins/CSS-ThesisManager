package pt.ul.fc.css.thesisman.business.services;


import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TemaDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private List<MestradoDTO> mestradosCompativeis;
	private Double remuneracaoMensal;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long novoId) {
		this.id = novoId;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String novoTitulo) {
		this.titulo = novoTitulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String novaDescricao) {
		this.descricao = novaDescricao;
	}
	
	public List<MestradoDTO> getMestradosCompativeis() {
		return mestradosCompativeis;
	}
	
	public void setMestradosCompativeis(List<MestradoDTO> novosMestradosCompativeis) {
		this.mestradosCompativeis = novosMestradosCompativeis;
	}
	
	public double getRemuneracaoMensal() {
		return remuneracaoMensal;
	}
	
	public void setRemuneracaoMensal(double novaRemuneracaoMensal) {
		this.remuneracaoMensal = novaRemuneracaoMensal;
	}

}
