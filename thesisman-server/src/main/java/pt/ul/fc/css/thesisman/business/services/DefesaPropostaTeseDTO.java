package pt.ul.fc.css.thesisman.business.services;

import java.util.Calendar;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;

@Component
public class DefesaPropostaTeseDTO {
	
	private Long id;
	private Calendar horaInicio;
	private Calendar horaFim;
	private Double nota;
	private ModalidadeApresentacao modalidadeApresentacao;
	private int duracao;
	private AlunoDTO aluno;
	private SalaDTO sala;
	private DocenteDTO elementoJuri;
	
	private String propostaTese;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long novoId) {
		this.id = novoId;
	}
	
	public Calendar getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(Calendar novaHoraInicio) {
		this.horaInicio = novaHoraInicio;
	}
	
	public Calendar getHoraFim() {
		return horaFim;
	}
	
	public void setHoraFim(Calendar novaHoraFim) {
		this.horaFim = novaHoraFim;
	}
	
	public Double getNota() {
		return nota;
	}
	
	public void setNota(Double novaNota) {
		this.nota = novaNota;
	}
	
	public ModalidadeApresentacao getModalidadeApresentacao() {
		return modalidadeApresentacao;
	}
	
	public void setModalidadeApresentacao(ModalidadeApresentacao novaModalidadeApresentacao) {
		this.modalidadeApresentacao = novaModalidadeApresentacao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public AlunoDTO getAluno() {
		return aluno;
	}
	
	public void setAluno(AlunoDTO novoAluno) {
		this.aluno = novoAluno;
	}
	
	public SalaDTO getSala() {
		return sala;
	}
	
	public void setSala(SalaDTO novaSala) {
		this.sala = novaSala;
	}
	
	public DocenteDTO getElementoJuri() {
		return elementoJuri;
	}
	
	public void setElementoJuri(DocenteDTO novoElementoJuri) {
		this.elementoJuri = novoElementoJuri;
	}


	public String getPropostaTese() {
		return propostaTese;
	}

	public void setPropostaTese(String propostaTese) {
		this.propostaTese = propostaTese;
	}

}
