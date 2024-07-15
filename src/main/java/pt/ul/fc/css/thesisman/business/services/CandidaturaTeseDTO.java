package pt.ul.fc.css.thesisman.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.entities.CandidaturaTese;
import pt.ul.fc.css.thesisman.entities.Tema;
import pt.ul.fc.css.thesisman.entities.Tese;

@Component
public class CandidaturaTeseDTO {
	
	private Long id;
	private AlunoDTO aluno;
	private List<TemaDTO> listaTemas;
	private TemaDTO temaAtribuido;
	private Tese tese; //TODO nao tem dto ?
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public AlunoDTO getAluno() {
		return aluno;
	}
	
	public void setAluno(AlunoDTO aluno) {
		this.aluno = aluno;
	}
	
	public List<TemaDTO> getListaTemas() {
		return listaTemas;
	}
	
	public void setListaTemas(List<TemaDTO> listaTemas) {
		this.listaTemas = listaTemas;
	}
	
	public TemaDTO getTemaAtribuido() {
		return temaAtribuido;
	}
	
	public void setTemaAtribuido(TemaDTO temaAtribuido) {
		this.temaAtribuido = temaAtribuido;
	}
	
	public Tese getTese() {
		return tese;
	}
	
	public void setTese(Tese tese) {
		this.tese = tese;
	}


}
