package pt.ul.fc.css.thesisman.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Subclass of Utilizador that represents Administrador 
 */
@Entity
@DiscriminatorValue("admin")
public class Administrador extends Utilizador{


    @Nullable
    @OneToMany
    private List<CandidaturaTese> candidaturasManuais = new LinkedList<>();

    /**
     * Empty constructor for the Administrador class
     * Creates an Administrador using the empty constructor of Utilizador
     */
    public Administrador() {
        super();
        candidaturasManuais = new LinkedList<>();
    }


    /**
     * Constructor for the class Administrador
     * Creates an Administrador object with the given parameters using
     * the Utilizador constructor
     * 
     * @param username String representing the username of Administrador
     * @param pass     String representing the password of this Administrador
     */
    public Administrador(@NonNull String username, @NonNull String pass) {
		super(username, pass);
	    candidaturasManuais = new LinkedList<>();
    }


    /**
     *  Getter for the manual Applications
     *
     * @return a List of the Applications
     */
    @Nullable
    public List<CandidaturaTese> getCandidaturasManuais() {
        return candidaturasManuais;
    }

    /**
     * Setter for the Applications List
     *
     * @param candidaturasManuais The List to set
     */
    public void setCandidaturasManuais(List<CandidaturaTese> candidaturasManuais) {
        this.candidaturasManuais = candidaturasManuais;
    }

    /**
     * Function to add an Application to the List
     *
     * @param c the Application to add
     */
    public boolean addCandidaturaManual(CandidaturaTese c) {
        if (candidaturasManuais == null) {
            return false;
        }
        candidaturasManuais.add(c);
        return true;
    }

    /**
     * To String for the Administrador
     * 
     * @return String representing the Administrador object and his attributes
     */
    @Override
    public String toString() {
        return "Administrador[" +
                "id = " + super.getId() + ", " +
                "username = " + super.getUsername() + ", " +
                "password = " + super.getPassword() + ", " +
                "login = " + super.getLogin() + ']';
    }

    /**
     * Equals function
     *
     * @param o Object that we want to compare this. to
     *
     * @return true if the object o is equal to this, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(candidaturasManuais, that.candidaturasManuais);
    }

    /**
     * Hash code function for Administrador
     *
     * @return int representing the hashcode of Administrador
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), candidaturasManuais);
    }
    

}
