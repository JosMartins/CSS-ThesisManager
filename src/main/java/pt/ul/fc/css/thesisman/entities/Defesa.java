package pt.ul.fc.css.thesisman.entities;


import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;


/**
 * This abstract class represents an appointment where Aluno can reason his Thesis
 * <p>
 * {@code horaInicio}             String representing the time when Defesa starts
 * {@code horaFim}                String representing the time when Defesa ends
 * {@code nota}                   Double representing the Defesa score
 * {@code modalidadeApresentacao} ModalidadeApresentacao representing if the appointment is local or remote
 * {@code duracao}                int representing the duration of Defesa
 * {@code aluno}                  Aluno representing the Aluno researching the Thesis 
 * {@code presidenteJuri}         Docente that will rate Defesa
 * {@code sala}                   Sala representing where Defesa will happen
 * {@code elementoJuri}           Docente that will help presidenteJuri rating Defesa
 */
@Entity
@DiscriminatorColumn(name = "TYPE")
public abstract class Defesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Calendar horaInicio;

    @NonNull
    private Calendar horaFim;

    @NonNull
    private Double nota;

    @NonNull 
    private ModalidadeApresentacao modalidadeApresentacao;

    private int duracao;


    @Nullable
    @OneToOne
    private Sala sala;

    @NonNull
    @OneToOne
    private Docente elementoJuri;


    /**
     * Empty constructor of the class Defesa 
     * Creates a Defesa  object with all default attributes
     * Uses Aluno, Docente, Sala and Tese Empty constructors
     */
    protected Defesa() {
        horaInicio = Calendar.getInstance();
        horaFim = Calendar.getInstance();
        nota = 0.0;
        modalidadeApresentacao = ModalidadeApresentacao.DEFAULT;
        duracao = 0;
        sala = new Sala();
        elementoJuri = new Docente();

    }


    /**
     * Constructor of the class Defesa 
     * Creates a Defesa  object with all parameters that it was given
     * nota is set to 0.0 by default
     * 
     * @param hInicio             String representing the time when Defesa starts
     * @param hFim                String representing the time when Defesa ends
     * @param modApres            ModalidadeApresentacao representing if the appointment is local or remote
     * @param dur                 int representing the duration of Defesa
     * @param juri                Docente that will help presidenteJuri rating Defesa
     */
    protected Defesa (@NonNull Calendar hInicio, @NonNull Calendar hFim, @NonNull ModalidadeApresentacao modApres, @NonNull int dur, @NonNull Docente juri) {
        horaInicio = hInicio;
        horaFim = hFim;
        nota = 0.0;
        modalidadeApresentacao = modApres;
        duracao = dur;
        sala = new Sala();
        elementoJuri = juri;

    }


    public void setId(Long id) {
		this.id = id;
	}


	public void setNota(Double nota) {
		this.nota = nota;
	}


	public void setModalidadeApresentacao(ModalidadeApresentacao modalidadeApresentacao) {
		this.modalidadeApresentacao = modalidadeApresentacao;
	}


	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	public void setElementoJuri(Docente elementoJuri) {
		this.elementoJuri = elementoJuri;
	}


	public Long getId() {
        return id;
    }

    /**
     * Gets the horaInicio of Defesa
     * 
     * @return horaInicio String representing the starting time of Defesa
     */
    @NonNull
    public Calendar getHoraInicio(){
        return horaInicio;
    }
    

    /**
     * Gets the horaFim of Defesa
     * 
     * @return horaFim String representing the ending time of Defesa
     */
    @NonNull
    public Calendar getHoraFim() {
        return horaFim;
    }
    
    
    /**
     * Gets the nota of Defesa
     * 
     * @return nota double representing the score of Defesa
     */
    public double getNota() {
        return nota;
    }


    /**
     * Gets the modalidadeApresentacao of Defesa
     * 
     * @return ModalidadeApresentacao representing if the appointment is local or remote
     */
    @NonNull
    public ModalidadeApresentacao getModalidadeApresentacao(){
        return modalidadeApresentacao;
    }


    /**
     * Gets the duracao of Defesa
     * 
     * @return duracao int representing the duration of Defesa
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Gets the sala of Defesa
     * 
     * @return Sala representing where Defesa will happen
     */
    @Nullable
    public Sala getSala() {
        return sala;
    }

    
    /**
     * Gets the elementoJuri of Defesa
     * 
     * @return elementoJuri Docente who helps presidenteJuri rating the Defesa
     */
    @NonNull
    public Docente getElementoJuri() {
        return elementoJuri;
    }


    /**
     * Sets horaInicio of Defesa to a new Calendar
     * 
     * @param horaInicio Calendar new starting time of Defesa
     */
    protected void setHoraInicio(@NonNull Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }


    /**
     * Sets horaFim of Defesa to a new Calendar
     * 
     * @param horaFim Calendar new ending time of Defesa
     */
    protected void setHoraFim(@NonNull Calendar horaFim) {
        this.horaFim = horaFim;
    }

    
    /**
     * Function that verifys if a Sala is available during a specific gap
     * 
     * @param horaInicio Calendar with the gap starting time
     * @param horaFim    Calendar with the gap ending time
     * 
     * @return true if Sala is available or false if it is not
     */
    protected boolean verificarSala(Calendar horaInicio,Calendar horaFim) {
        if (sala == null) {
            return false;
        }

        return sala.verificarDisponibilidade(horaInicio,horaFim);
    }


    public void setSala(@Nullable Sala sala) {
        this.sala = sala;
    }

    /**
     * Equals function for Defesa
     * 
     * @param obj Object that we want to compare this. to
     * 
     * @return true if both objects are equals or false if they are not
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Defesa) obj;
        return horaInicio.equals(that.horaInicio) &&
                horaFim.equals(that.horaFim) &&
                nota.equals(that.nota) &&
                modalidadeApresentacao == that.modalidadeApresentacao &&
                duracao == that.duracao &&
                (sala == null || sala.equals(that.sala)) &&
                elementoJuri.equals(that.elementoJuri);
    }


    /**
     * Hash code function for Defesa
     * 
     * @return int representing the hashcode of Defesa
     */
    @Override
    public int hashCode() {
        return Objects.hash(horaInicio, horaFim, nota, modalidadeApresentacao, duracao, sala, elementoJuri);
    }

}

