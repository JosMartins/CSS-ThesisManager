package pt.ul.fc.css.thesisman.business.services;

import java.util.Calendar;

import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;

public class DefesaTeseDTO {


	private Long id;
	//TODO Será que é preciso meter @NotNull em algum lado?
	private Calendar horaInicio;
	private Calendar horaFim;
	private Double nota;
    private ModalidadeApresentacao modalidadeApresentacao; //TODO nao tem DTO
    private int duracao;
	private AlunoDTO aluno;
	private SalaDTO sala;
	private DocenteDTO elementoJuri;
	private DocenteDTO presidenteJuri;
    
    
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
	
	public void setDuracao(int novaDuracao) {
		this.duracao = novaDuracao;
	}
	
	public AlunoDTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoDTO aluno) {
		this.aluno = aluno;
	}

	public SalaDTO getSala() {
		return sala;
	}

	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}

	public DocenteDTO getElementoJuri() {
		return elementoJuri;
	}

	public void setElementoJuri(DocenteDTO elementoJuri) {
		this.elementoJuri = elementoJuri;
	}
	
	public DocenteDTO getPresidenteJuri() {
		return presidenteJuri;
	}
	
	public void setPresidenteJuri(DocenteDTO presidente) {
		presidenteJuri = presidente;
	}

    
}
