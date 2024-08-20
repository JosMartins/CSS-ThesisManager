package pt.ul.fc.css.thesisman.entities;

import java.util.Objects;

import org.springframework.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Class representing an Empresa that UtilizadorEmpresarial can belong to
 * It represents a table with all Empresas
 * <p>
 * {@code id}      Long representing a specific id of each Empresa in the table
 * {@code nome}    String representing the name of Empresa
 * {@code enderco} String representing the address of Empresa
 */
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @NonNull
	private String nome;

    @NonNull
	private String endereco;


    /**
     * Empty constructor for the class Empresa
     * Creates an Empresa object with all default attributes
     */
    public Empresa () {
        nome = "";
        endereco = "";
    }
	

    /**
     * Empty constructor for the class Empresa
     * Creates an Empresa object with all default attributes
     * 
     * @param nomeEmpresa     String representing the name of Empresa
     * @param enderecoEmpresa String representing the address of Empresa 
     */
	public Empresa (@NonNull String nomeEmpresa, @NonNull String enderecoEmpresa) {
		nome = nomeEmpresa;
		endereco = enderecoEmpresa;
	}
	
    /**
     * Empty constructor for the class Empresa
     * Creates an Empresa object with all attributes
     * 
     * @param nomeEmpresa     String representing the name of Empresa
     */
	public Empresa (@NonNull String nomeEmpresa) {
		nome = nomeEmpresa;
		endereco = "default_endereco";
	}


    /**
     * Gets the id of Empresa in the table
     * 
     * @return Long representing the id of Empresa in the table
     */
    public Long getId() {
        return id;
    }
	

    /**
     * Gets the name of Empresa
     * 
     * @return String representing the name of Empresa
     */
	public String getNome() {
		return nome;
	}
	

    /**
     * Sets a new name for Empresa
     * 
     * @param novoNome new name for Empresa
     */
	public void setNome(String novoNome) {
		nome = novoNome;
	}


    /**
     * Gets the address of Empresa
     * 
     * @return String representing the address of Empresa
     */
	@NonNull
    public String getEndereco() {
		return endereco;
	}
	

    /**
     * Sets the address of Empresa
     *
     * @param novoEndereco String representing the new address for Empresa
     */
	public void setEndereco(String novoEndereco) {
		endereco = novoEndereco;	
	}
	
	public void setId(Long novoId) {
		id = novoId;
	}



    /**
     * Equals function for Empresa
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
        var that = (Empresa) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.nome, that.nome) &&
                Objects.equals(this.endereco, that.endereco);
    }



    /**
     * Hash code function for Empresa
     * 
     * @return int representing the hashcode of Empresa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, endereco);
    }

    

    /**
     * To String for the Empresa
     * 
     * @return String representing the Empresa object and his attributes
     */
    @Override
    public String toString() {
        return "Empresa[" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "endereco = " + endereco + ']';
    }
    

}
