package pt.ul.fc.css.thesisman.datatypes;

import jakarta.persistence.Embeddable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


/**
* Class representing a slot in time where Defesas can happen
* <p>
* {@code inicioTempo} Calendar representing the time when the slot starts
* {@code fimTempo}   Calendar representing the time when the slot ends
* 
*/
@Embeddable
public class SlotTempo {

	private Calendar inicioTempo = Calendar.getInstance();
	private Calendar fimTempo = Calendar.getInstance();


    /**
    * Empty constructor for the SlotTempo class
    * Creates a SlotTempo object with all default attributes
    */
    protected SlotTempo() {
    }


    /**
    * Constructor for the class SlotTime
    * Creates a SlotTime object with the given parameters
    * 
    * @param inicio Calendar representing the time when the slot starts
    * @param fim   Calendar representing the time when the slot ends
    */
    public SlotTempo(Calendar inicio, Calendar fim) {
        inicioTempo = inicio;
        fimTempo = fim;
    }


    /**
     * Gets inicioTempo of SlotTime
     * 
     * @return inicioTempo Calender inicioTempo of SlotTime
     */
    public Calendar getInicioTempo() {
        return inicioTempo;
    }


    /**
     * Gets fimTempo of SlotTime
     * 
     * @return fimTempo Calender fimTempo of SlotTime
     */
    public Calendar getFimTempo() {
        return fimTempo;
    }


    /**
     * Sets inicioTempo of SlotTime to a new Calendar
     * 
     * @param tempo Calendar new inicio tempo
     */
    public void setInicioTempo(Calendar tempo) {
        inicioTempo = tempo;
    }

     /**
     * Sets fimTempo of SlotTime to a new Calendar
     * 
     * @param tempo Calendar new fim tempo
     */
    public void setFimTempo(Calendar tempo) {
        fimTempo = tempo;
    }

    
    /**
    * Equals function for SlotTempo
    * 
    * @param obj Object that we want to compare this. to
    * 
    * @return true if both objects are equals or false if they are not
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SlotTempo slotTempo = (SlotTempo) obj;
        return Objects.equals(inicioTempo, slotTempo.inicioTempo) &&
                Objects.equals(fimTempo, slotTempo.fimTempo);
    }

    
    /**
    * Hash code function for SlotTempo
    * 
    * @return int representing the hashcode of SlotTempo
    */
    @Override
    public int hashCode() {
        return Objects.hash(inicioTempo, fimTempo);
    }

    
    /**
    * To String for the SlotTempo
    * 
    * @return String representing the SlotTempo object and his attributes
    */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "SlotTempo[" +
                "startTime=" + dateFormat.format(inicioTempo.getTime()) +
                ", endTime=" + dateFormat.format(fimTempo.getTime()) +
                ']';
    }


    public boolean overlaps(SlotTempo slot) {
        //TODO: test this
        return (this.fimTempo.before(slot.getInicioTempo()) || this.fimTempo.equals(slot.getInicioTempo())) &&
                (this.inicioTempo.after(slot.getFimTempo()) || this.inicioTempo.equals(slot.getFimTempo()));
    }
}
