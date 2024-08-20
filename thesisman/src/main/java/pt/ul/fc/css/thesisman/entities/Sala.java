package pt.ul.fc.css.thesisman.entities;


import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import pt.ul.fc.css.thesisman.datatypes.SlotTempo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;



/**
 * Class representing the place where Defesa will happen
 * It represents a table with all Salas
 * {@code id}         Long representing the Sala
 * {@code designacao} String representing the Sala designation
 */
@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String designacao;

    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    private List<SlotTempo> ocupacao;

    
    /**
     * Empty constructor for the class Sala
     * Creates a Sala object with all default attributes
     */
    public Sala() {
        designacao = "";
        ocupacao = new ArrayList<>();
    }

    
    /**
     * Constructor for the class Sala
     * Creates a Sala object with the given name
     * ocupacao is set to an empty ArrayList
     * 
     * @param nome String degisnation of Sala
     */
    public Sala(@NonNull String nome) {
        designacao = nome;
        ocupacao = new ArrayList<>();
    }


    /**
     * Gets the id of Sala
     * 
     * @return Long representing the Sala id on the table
     */
    public Long getId() {
        return id;
    }



    /**
     * Gets designacao of Sala
     * 
     * @return designacao String representing the Sala designation
     */
    @NonNull
    public String getDesignacao() {
        return designacao;
    }

    
    /**
     * Sets designacao of Sala to a new designacao
     * 
     * @param designacao String representing the new desigation
     */
    public void setDesignacao(@NonNull String designacao) {
        this.designacao = designacao;
    }
    
    public void setId(Long novoId) {
    	id = novoId;
    }
    
    public void setSlotTempo (List<SlotTempo> slots) {
    	ocupacao = slots;
    }



    /**
     * Gets ocupacao of Sala
     * 
     * @return ocupacao List of TimeSlot representing the booked slots
     */
    @NonNull
    public List<SlotTempo> getOcupacao() {
        return ocupacao;
    }


    /**
     * Function that books a slot and stores it in ocupacao
     * 
     * @param inicio Calendar representing the time when the slot starts
     * @param fim   Calendar representing the time when the slot ends
     * 
     * @return true if the slot was booked successfully or false otherwise
     */
    public boolean marcarOcupacao(Calendar inicio, Calendar fim) {
        if (verificarDisponibilidade(inicio, fim)) {
            SlotTempo ts = new SlotTempo(inicio, fim);
            ocupacao.add(ts);
            return true;

        }
        return false;
    }
    
    
    /**
     * Function that verifys if a time slot is free
     * 
     * @param horaInicio Calendar representing the time when the slot starts
     * @param horaFim    Calendar representing the time when the slot ends
     * 
     * @return true if the slot is free or false otherwise
     */
    public boolean verificarDisponibilidade(Calendar horaInicio, Calendar horaFim) {


        if (horaInicio.compareTo(horaFim) >= 0 || horaInicio.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                horaInicio.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }
        SlotTempo toAdd = new SlotTempo(horaInicio, horaFim);
        if (ocupacao.isEmpty()) {
            return true;
        }

        for (SlotTempo ts : ocupacao) {
            if (ts.getFimTempo().before(toAdd.getInicioTempo()) || ts.getInicioTempo().after(toAdd.getFimTempo())) {
                ocupacao.add(toAdd);
                return true;
            }
        }
        return false;
    }

    

    /**
     * Equals function for Sala
     * 
     * @param obj Object that we want to compare this. to
     * 
     * @return true if both objects are equals or false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sala sala = (Sala) obj;
        return Objects.equals(id, sala.id) &&
                Objects.equals(designacao, sala.designacao) &&
                Objects.equals(ocupacao, sala.ocupacao);
    }



    /**
     * Hash code function for Sala
     * 
     * @return int representing the hashcode of Sala
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, designacao, ocupacao);
    }



    /**
     * To String for the Sala
     * 
     * @return String representing the Sala object and his attributes
     */
    @Override
    public String toString() {
        return "Sala [" +
                "id = " + id + ", " +
                "designacao = " + designacao + ", " +
                ocupacao + ']';
    }

}
