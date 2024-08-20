package pt.ul.fc.css.thesisman.entities;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import org.springframework.lang.NonNull;



/**
 * Subclass of Utilizador that represents UtilizadorEmpresarial
 * <p>
 * {@code empresa} Empresa that UtilizadorEmpresarial belongs to
 */
@Entity
@DiscriminatorValue("utilizadorEmpresarial")
public class UtilizadorEmpresarial extends Utilizador {

    @NonNull
	@OneToOne
	private Empresa empresa;


    /**
     * Empty constructor for the UtilizadorEmpresarial class
     * Creates a UtilizadorEmpresarial using the empty constructor of Utilizador
     * empresa is set by default to a new empty Empresa
     */
    public UtilizadorEmpresarial() {
		super();
        empresa = new Empresa();
	}


    /**
     * Constructor for the class UtilizadorEmpresarial
     * Creates a UtilizadorEmpresarial object with the given parameters using the 
     * Utilizador constructor
     * 
     * @param username String representing the username of Docente
     * @param pass     String representing the password of this Docente
     * @param emp      Empresa that UtilizadorEmpresarial belongs to 
     * 
     */
	public UtilizadorEmpresarial(@NonNull String username, @NonNull String pass, @NonNull Empresa emp) {
		super(username, pass);
		empresa = emp;
	}
	

	/**
	 * Gets the empresa of  UtilizadorEmpresarial
	 * 
	 * @return Empresa that UtilizadorEmpresarial belongs
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	public String getNomeEmpresa() {
		return empresa.getNome();
	}

	public void setNomeEmpresa(@NonNull String nome) {
		empresa.setNome(nome);
	}
    
    /**
     * Sets the empresa to a new Empresa
     * 
     * @param novaEmpresa New Empresa that we want UtilizadorEmpresarial
     * to belong to
     */
	public void setEmpresa(@NonNull Empresa novaEmpresa) {
		empresa = novaEmpresa;
	}



	/**
     * Equals function for UtilizadorEmpresarial
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
        var that = (UtilizadorEmpresarial) obj;
        return Objects.equals(this.empresa, that.empresa);
    }



	/**
     * Hash code function for UtilizadorEmpresarial
     * 
     * @return int representing the hashcode of UtilizadorEmpresarial
     */
	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),empresa);
    }



	/**
     * To String for the UtilizadorEmpresarial
     * 
     * @return String representing the UtilizadorEmpresarial object and his attributes
     */
	@Override
    public String toString() {
        return "Utilizador Empresarial[" +
                "id = " + super.getId() + ", " +
                "username = " + super.getUsername() + ", " +
                "password = " + super.getPassword()+ ", " +
                "login = " + super.getLogin() +  ", " +
				empresa + ']';
    }


}
