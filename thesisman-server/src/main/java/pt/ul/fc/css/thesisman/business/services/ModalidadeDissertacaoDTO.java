package pt.ul.fc.css.thesisman.business.services;

import java.util.List;

public class ModalidadeDissertacaoDTO {
	
	private Long id;
	private TemaDTO tema;
	private AlunoDTO aluno;
	private DocenteDTO orientador;
	private String documento;
	private List<DefesaPropostaTeseDTO> defesaPropostasTeseId;
	private DefesaTeseDTO defesaTese;


	public Long getId() {
		return id;
	}
	
	public void setId(Long novoId) {
		this.id = novoId;
	}
	
	public TemaDTO getTema() {
		return tema;
	}
	
	public void setTema(TemaDTO novoTema) {
		this.tema = novoTema;
	}
	
	public AlunoDTO getAluno() {
		return aluno;
	}
	
	public void setAluno(AlunoDTO novoAluno) {
		this.aluno = novoAluno;
	}
	
	public DocenteDTO getOrientador() {
		return orientador;
	}
	
	public void setDocente(DocenteDTO novoOrientador) {
		this.orientador = novoOrientador;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public void setDocumento(String novoDocumento) {
		this.documento = novoDocumento;
	}
	
	public List<DefesaPropostaTeseDTO> getDefesaPropostasTeseId() {
		return defesaPropostasTeseId;
	}
	
	public void setDefesaPropostasTeseId(List<DefesaPropostaTeseDTO> novaDefesaPropostasTeseId) {
		this.defesaPropostasTeseId = novaDefesaPropostasTeseId;
	}
	
	public DefesaTeseDTO getDefesaTese() {
		return defesaTese;
	}
	
	public void setDefesaTese(DefesaTeseDTO novaDefesaTese) {
		this.defesaTese = novaDefesaTese;
	}

}
