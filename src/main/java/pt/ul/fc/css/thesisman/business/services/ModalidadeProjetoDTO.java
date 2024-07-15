package pt.ul.fc.css.thesisman.business.services;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ModalidadeProjetoDTO {
	
	private Long id;
	private TemaDTO tema;
	private AlunoDTO aluno;
	private DocenteDTO orientador;
	private String documento;
	private List<DefesaPropostaTeseDTO> defesaPropostasTese;
	private DefesaTeseDTO defesaTese;
	private UtilizadorEmpresarialDTO orientadorEmpresarial;

	
	
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

	public void setOrientador(DocenteDTO novoOrientador) {
		this.orientador = novoOrientador;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String novoDocumento) {
		this.documento = novoDocumento;
	}
	public List<DefesaPropostaTeseDTO> getDefesaPropostasTese() {
		return defesaPropostasTese;
	}

	public void setDefesaPropostasTese(List<DefesaPropostaTeseDTO> novaDefesaPropostasTeseId) {
		this.defesaPropostasTese = novaDefesaPropostasTeseId;
	}

	public DefesaTeseDTO getDefesaTese() {
		return defesaTese;
	}

	public void setDefesaTese(DefesaTeseDTO novaDefesaTese) {
		this.defesaTese = novaDefesaTese;
	}

	public UtilizadorEmpresarialDTO getOrientadorEmpresarial() {
		return orientadorEmpresarial;
	}

	public void setOrientadorEmpresarial(UtilizadorEmpresarialDTO novoOrientadorEmpresarial) {
		this.orientadorEmpresarial = novoOrientadorEmpresarial;
	}

}
