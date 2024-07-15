package pt.ul.fc.css.thesisman.entities;

import java.util.LinkedList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import pt.ul.fc.css.thesisman.datatypes.SlotTempo;



/**
 * Subclass of Utilizador that represents Docente 
 */
@Entity
@DiscriminatorValue("docente")
public class Docente extends Utilizador{

	@ElementCollection(fetch = FetchType.EAGER)
	//@CollectionTable(name = "docente_time_slots", joinColumns = @JoinColumn(name = "docente_id"))
    private List<SlotTempo> timeSlots = new LinkedList<>();

    /**
     * Empty constructor for the Docente class
     * Creates a Docente using the empty constructor of Utilizador
     */
    public Docente() {
        super();
    }


    /**
     * Constructor for the class Docente
     * Creates a Docente object with the given parameters using
     * the Utilizador constructor
     * 
     * @param username String representing the username of Docente
     * @param pass     String representing the password of this Docente
     */
    public Docente(@NonNull String username, @NonNull String pass) {
		super(username, pass);
	}

    public List<SlotTempo> getTimeSlots() {
        return timeSlots;
    }

    public boolean addSlot(SlotTempo slot) {
        return timeSlots.add(slot);
    }

    public boolean verifyAvalability(SlotTempo slot) {
        for (SlotTempo s : timeSlots) {
            if (s.overlaps(slot)) {
                return false;
            }
        }
        return true;
    }
    
    public void setTimeSlots (List<SlotTempo> slots) {
    	timeSlots = slots;
    }
    /**
     * To String for the Docente
     * 
     * @return String representing the Docente object and his attributes
     */
    @Override
    public String toString() {
        return "Docente[" +
                "id = " + super.getId() + ", " +
                "username = " + super.getUsername() + ", " +
                "password = " + super.getPassword() + ", " +
                "login = " + super.getLogin() + ']';
    }

}
