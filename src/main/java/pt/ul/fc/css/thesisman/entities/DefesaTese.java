package pt.ul.fc.css.thesisman.entities;


import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;

import java.util.Calendar;
import java.util.Objects;



/**
 * Subclass of Defesa representing a reasoning of a proposal of Thesis
 * This class makes part of the Defesa table
 * <p>
 * {@code DURATION} int representing the duration of the reasoning
 */
@Entity
@DiscriminatorValue("DEFESA_TESE")
public class DefesaTese extends Defesa {

    private static final int DURATION = 90; //MINUTES

    @NonNull
    @OneToOne
    private Docente presidenteJuri;

    
    /**
     * Empty constructor for the DefesaTese class
     * Creates a DefesaTese using the empty constructor of Defesa and Docente
     */
    public DefesaTese() {
        super();
        presidenteJuri = new Docente();
    }

    public DefesaTese(@NonNull Docente pJ) {
        super();
        presidenteJuri = pJ;
    }


    /**
     * Function to book a Sala for DefesaTese
     * If DefesaTese is of local type it check if there is an Empty room and then books it
     * 
     * @param horaInicio             Calendar starting time of the reasoning
     * @param modalidadeApresentacao ModalidadeApresentacao type fo presentation that can be either local or remote
     * 
     * @return true in case of success booking or false otherwise
     */
    public boolean marcar(Calendar horaInicio, ModalidadeApresentacao modalidadeApresentacao) {
        Calendar horaFim = (Calendar) horaInicio.clone();
        horaFim.add(Calendar.MINUTE, DURATION);
        if (modalidadeApresentacao == ModalidadeApresentacao.REMOTO) {
            super.setHoraInicio(horaInicio);
            super.setHoraFim(horaFim);
            return true;
        }
        if (super.verificarSala(horaInicio, horaFim)) {
            super.setHoraInicio(horaInicio);
            super.setHoraFim(horaFim);
            return true;
        }
        return false;
    }


    /**
     * Gets the presidenteJuri of Defesa
     *
     * @return presidenteJuri Docente who will rate the Defesa
     */
    @NonNull
    public Docente getPresidenteJuri() {
        return presidenteJuri;
    }
    

    /**
     * Sets presidenteJuri of DefesaTese to a new Docente
     * 
     * @param presidente Docente new Teacher becoming presidenteJuri
     */
    public void setPresidenteJuri(@NonNull Docente presidente) {
        presidenteJuri = presidente;
    }


    /**
     * To String for the DefesaTese
     * 
     * @return String representing the DefesaTese object and his attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DefesaTese that = (DefesaTese) o;
        return Objects.equals(presidenteJuri, that.presidenteJuri);
    }


    /**
     * Hash code function for DefesaTese
     * 
     * @return int representing the hashcode of DefesaTese
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), presidenteJuri);
    }


    /**
     * To String for the DefesaTese
     * 
     * @return String representing the DefesaTese object and his attributes
     */
    @Override
    public String toString() {
        String sala = "";
        if(super.getSala() != null) {
           sala =  "sala = " + super.getSala().toString() + ", ";
        }
        return "Defesa Tese[" +
                "duracão = " + DURATION + ", " +
                "nota = " + super.getNota() + ", " +
                "modalidade de apresentação = " + super.getModalidadeApresentacao() + ", " +
                sala +
                "presidente juri = " + presidenteJuri + ", " +
                "elemento juri = " + super.getElementoJuri() + ']';
    }

}



