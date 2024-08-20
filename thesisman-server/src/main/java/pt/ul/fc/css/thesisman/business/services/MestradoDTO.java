package pt.ul.fc.css.thesisman.business.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class MestradoDTO {

	private Long id;
	private String nome;
	private List<AlunoDTO> alunosInscritos;


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

	public List<AlunoDTO> getAlunosInscritos() {
		return alunosInscritos;
	}

	public void setAlunosInscritos(List<AlunoDTO> novosAlunosInscritos) {
		this.alunosInscritos = novosAlunosInscritos;
	}

}
