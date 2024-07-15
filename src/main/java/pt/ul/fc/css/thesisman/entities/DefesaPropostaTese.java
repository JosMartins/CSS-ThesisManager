package pt.ul.fc.css.thesisman.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;

import java.util.Calendar;


/**
 * Subclass of Defesa representing a reasoning of a proposal of Thesis
 * This class makes part of the Defesa table
 * <p>
 * {@code DURATION} int representing the duration of the reasoning
 */

@Entity
@DiscriminatorValue("DEFESA_PROPOSTA_TESE")
public class DefesaPropostaTese extends Defesa{

    private static final int DURATION = 60; //MINUTES
    private String propostaTese;

    /**
     * Empty constructor for the DefesaPropostaTese class
     * Creates a DefesaPropostaTese using the empty constructor of Defesa
     */
    public DefesaPropostaTese() {
        super();
    }


    /**
     * Function to book a Sala for DefesaPropostaTese
     * If DefesaPropostaTese is of local type it check if there is an Empty room and then books it
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
        if (super.verificarSala(horaInicio, horaFim)){
            super.setHoraInicio(horaInicio);
            super.setHoraFim(horaFim);
            return true;
        }
        return false;
    }
    
  

    /**
     * To String for the DefesaPropostaTese
     * 
     * @return String representing the DefesaPropostaTese object and his attributes
     */
    @Override
    public String toString() {
        String sala = "";
        if(super.getSala() != null) {
           sala =  "sala = " + super.getSala().toString() + ", ";
        }
        return "Defesa Proposta Tese[" +
                "duracão = " + DURATION + ", " +
                "nota = " + super.getNota() + ", " +
                "modalidade de apresentação = " + super.getModalidadeApresentacao() + ", " +
                sala +
                "elemento juri = " + super.getElementoJuri() + ']';
    }


	public String getPropostaTese() {
		return propostaTese;
	}

	public void setPropostaTese(String propostaTese) {
		this.propostaTese = propostaTese;
	}
}
