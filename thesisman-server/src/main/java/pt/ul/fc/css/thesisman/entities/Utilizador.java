package pt.ul.fc.css.thesisman.entities;


import  jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;


/**
 * This abstract class represents a user that can interact in the Thesys Application
 * It represents a table with all Utilizadores
 * <p>
 * {@code id}        Long representing a specific id of each user in the table
 * {@code username}  String representing the username of user
 * {@code password}  String representing the password of user
 * {@code login}     Boolean representing if the user is logged in or not
 */
@Entity
@Table(name = "Utilizadores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_UTILIZADOR")
public abstract class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private Long id;

    @NonNull
	private String username;

    @NonNull
	private String password;

	private Boolean login;

    
    /**
     * Empty constructor for the class Utilizador
     * Creates an Utilizador object with all default attributes
     */
    protected Utilizador() {
        username = "";
        password = "";
        login = false;
    }


    /**
     * Constructor for the class Utilizador
     * Creates a Utilizador object with the given parameters
     * Login is set to false by default
     * 
     * @param id   String representing the specific id in the table for this Utilizador
     * @param pass String representing the password of this Utilizador
     * 
     */
	protected Utilizador(@NonNull String id, @NonNull String pass) {
		username = id;
		password = pass;
		login = false;
	}


    /**
     * Constructor for the class Utilizador
     * Creates an Utilizador object with the given parameters
     * <p>
     * {@code id} String representing the specific id in the table for this Utilizador
     * {@code pass} String representing the password of this Utilizador
     * {@code estado} boolean representing the user activity, true if Utilizador is logged
     * or false if it is not
     */
    protected Utilizador(@NonNull String id, @NonNull String pass, boolean estado) {
        username = id;
		password = pass;
		login = estado;
    }


    /**
     * Gets the id of Utilizador
     * 
     * @return Long representing the Utilizador id on the table
     */
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	/**
     * Gets the username of Utilizador
     * 
     * @return String representing the username of Utilizador
     */
    @NonNull
	public String getUsername() {
		return username;
	}



    /**
     * Gets the password of Utilizador
     * 
     * @return String representing the password of Utilizador
     */
    @NonNull
    public String getPassword() {
        return password;
    }


    /**
     * Gets the activity of user
     * 
     * @return true if Utilizador is logged in or false if it is not
     */
    public boolean getLogin() {
		return login;
	}

    
    /**
     * Function that updates the username of Utilizador
     * 
     * @param novoId String representing the new username of Utilizador
     * @param pass   String used to verify if the user is valid
     * 
     * @return true if the username was changed successfully or false if it was not
     */ 
	public boolean updateUsername(String novoId, String pass) {

		if(password.equals(pass)) {
			username = novoId;
			return true;
		}
		return false;
	}

    
    /**
     * Function that updates the password of Utilizador
     * 
     * @param id         String representing the id of Utilizador in the table
     * @param passAntiga String representing the old password
     * @param novaPass   String representing the new password
     *
     * @return true if the password was changed successfully or false if it was not
     */
	public boolean updatePassword(String id, String passAntiga, String novaPass) {

		if (username.equals(id) && password.equals(passAntiga) && !passAntiga.equals(novaPass)) {
			password = novaPass;
			return true;
		}

		return false;
	}


    /**
     * Function that verifys if the Utilizador is valid
     * 
     * @param name String representing the username of Utilizador in the table
     * @param pass String representing the password of Utilizador
     * 
     * @return true if the Utilizador is valid or false if it does not
     */
	public boolean verificaUtilizador(String name, String pass) {
		return username.equals(name) && password.equals(pass);
	}

    
    /**
     * Function that changes the activity of Utilizador
     * 
     * @param estado Boolean representing the new state of login of Utilizador
     */
	public void setLogin(boolean estado) {
		login = estado;
	}



    /**
     * Equals function for Utilizador
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
        var that = (Utilizador) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                this.login == that.login;
    }



    /**
     * Hash code function for Utilizador
     * 
     * @return int representing the hashcode of Utilizador
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, login);
    }
}


