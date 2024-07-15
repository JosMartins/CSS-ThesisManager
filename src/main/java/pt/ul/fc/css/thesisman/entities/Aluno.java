package pt.ul.fc.css.thesisman.entities;

import java.util.Objects;

import jakarta.persistence.*;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * Subclass of Utilizador that represents Aluno
 * <p>
 * {@code mestrado} Mestrado that Aluno belongs to
 * {@code media}    Avegare score of Aluno
 * 
 */
@Entity
@DiscriminatorValue("aluno")
public class Aluno extends Utilizador{

	@ManyToOne
	@JoinColumn(name = "mestrado_id")
	private Mestrado mestrado;

	private Double media;
	
	private String propostraTese;

	@Nullable
	@OneToOne(fetch = FetchType.EAGER)
	private Tese tese;


	/**
	 * Empty constructor for the Aluno class
	 * Creates an Aluno object with all default attributes
	 */
	public Aluno () {
		super();
		media = 0.0;
		mestrado = null;
	}


	/**
	 * Constructor for the class Aluno
	 * Creates an Aluno object with the given parameters
	 * media is set to 0.0 by default
	 * mestrado is set to null by default
	 * 
	 * @param username String representing the username of Aluno
	 * @param pass     String representing the password of this Aluno
	 */    
	public Aluno (@NonNull String username, @NonNull String pass) {
		super(username, pass);
		media = 0.0;
		mestrado = null;
	}


	/**
	 * Constructor for the class Aluno
	 * Creates an Aluno object with the given parameters
	 * media is set to 0.0 by default
	 * 
	 * @param username String representing the username of Aluno
	 * @param pass     String representing the password of this Aluno
	 * @param m        Mestrado representing the Mestrado where Aluno belongs
	 */
	public Aluno (@NonNull String username, @NonNull String pass, Mestrado m) {
		super(username, pass);
		media = 0.0;
		mestrado = m;
	}


	/**
	 * Constructor for the class Aluno
	 * Creates an Aluno object with the given parameters
	 * 
	 * @param username   String representing the username of Aluno
	 * @param pass       String representing the password of this Aluno
	 * @param m          Mestrado representing the Mestrado where Aluno belongs
	 * @param mediaAluno double representing the average score of Aluno
	 */
	public Aluno (@NonNull String username, @NonNull String pass, Mestrado m, double mediaAluno) {
		super(username, pass);
		media = mediaAluno;
		mestrado = m;
	}


	/**
	 * Getter for Mestrado
	 * 
	 * @return Mestrado that Aluno belongs to 
	 */
	public Mestrado getMestrado() {
		return mestrado;
	}


	/**
	 * Getter for media
	 * 
	 * @return double representing the average score of Aluno
	 */
	public double getMedia() {
		return media;
	}


	/**
	 * Setter for Mestrado
	 * 
	 * @param novoMestrado New Mestrado that we want Aluno to belong to
	 */
	public void setMestrado(Mestrado novoMestrado) {
		mestrado = novoMestrado;
	}


	/**
	 * Function that updates media
	 * 
	 * @param nota double representing a new score to be added to
	 * the average
	 */
	public void updateMedia(double nota) {
		media = (media + nota)/2; 
	}


	/**
	 * Function that set media of Aluno
	 * 
	 * @param nota double representing a new score to be added to
	 * the average
	 */
	public void setMedia (double nota) {
		media = nota;
	}


	/**
	 * Equals function for Aluno
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
		if (!super.equals(obj))
			return false;
		var that = (Aluno) obj;
		return Objects.equals(this.mestrado, that.mestrado) &&
				this.media == that.media;
	}



	/**
	 * Hash code function for Aluno
	 * 
	 * @return int representing the hashcode of Aluno
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(),mestrado, media);
	}


	/**
	 * To String for the Aluno
	 * 
	 */
	@Override
	public String toString() {
		return "Aluno[" +
				"id = " + super.getId() + ", " +
				"username = " + super.getUsername() + ", " +
				"password = " + super.getPassword()+ ", " +
				"login = " + super.getLogin() +  ", " +
				"media = " + media +
				(mestrado != null ? ", " + mestrado + ']' : ']');
	}


	public String getPropostraTese() {
		return propostraTese;
	}


	public void setPropostraTese(String propostraTese) {
		this.propostraTese = propostraTese;
	}


	public Tese getTese() {
		return tese;
	}


	public void setTese(Tese tese) {
		this.tese = tese;
	}
}