package pt.ul.fc.css.thesisman.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

import org.springframework.lang.NonNull;


/**
 * Class representing a thema available for a Thesys
 * It represents a table with all Temas
 * <p>
 * {@code id}                   Long representing the id of Tema in table
 * {@code titulo}               String representing the title of this Tema
 * {@code descricao}            String representing a description of this Tema
 * {@code mestradosCompativeis} List of Mestrado where this Tema is available
 * {@code remuneracaoMensal}    double representing the remuneration of this Tema
 */
@Entity
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
    @NonNull
	private String titulo;

    @NonNull
	private String descricao;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MESTRADOS_COMPATIVEIS",
            joinColumns = @JoinColumn(name = "tema_id")
    )
	private List<Mestrado> mestradosCompativeis;

	private Double remuneracaoMensal;
	

    /**
     * Empty constructor for the class Tema
     * Creates a Tema object with all default attributes
     *
     */
    public Tema () {
        titulo = "";
        descricao = "";
        mestradosCompativeis = new LinkedList<>();
        remuneracaoMensal = 0.0;

    }
	

	/**
	 * Constructor for the class Tema
	 * Creates a Tema object with the given parameters
	 * remuneracaoMental is set to 0.0
	 * 
     * @param tituloTema               String representing the title of this Tema
     * @param descricaoTema            String representing a description of this Tema
     * @param mestrados List of Mestrado where this Tema is available
	 */
	public Tema (@NonNull String tituloTema, @NonNull String descricaoTema, @NonNull List<Mestrado> mestrados) {
		titulo = tituloTema;
		descricao = descricaoTema;
		mestradosCompativeis = mestrados;
		remuneracaoMensal = 0.0;
	}


    /**
	 * Constructor for the class Tema
	 * Creates a Tema object with the given parameters
	 * 
     * @param tituloTema               String representing the title of this Tema
     * @param descricaoTema            String representing a description of this Tema
     * @param mestrados List of Mestrado where this Tema is available
     * @param remuneracao    double representing the remuneration of this Tema
	 */
    public Tema (@NonNull String tituloTema, @NonNull String descricaoTema, @NonNull List<Mestrado> mestrados, double remuneracao) {
		titulo = tituloTema;
		descricao = descricaoTema;
		mestradosCompativeis = mestrados;
		remuneracaoMensal = remuneracao;
	}


    /**
     * Gets the id of Tema
     * 
     * @return Long representing the Tema id on the table
     */
    public Long getId() {
        return id;
    }

    
    /**
     * Gets the title of Tema
     * 
     * @return String representing the title of Tema
     */
	@NonNull
    public String getTitulo() {
		return titulo;
	}

    
    /**
     * Gets the description of Tema
     * 
     * @return String representing the description of Tema 
     */
    @NonNull
    public String getDescricao() {
		return descricao;
	}


    /**
     * Gets the list of Mestrado where this Tema is available
     * 
     * @return mestradosCompativeis List of Mestrado 
     */
    @NonNull
    public List<Mestrado> getMestradosCompativeis(){
		return mestradosCompativeis;
	}

    
    /**
     * Gets the remuneracaoMensal of Tema
     * 
     * @return remuneracaoMensal double representing the remuneration of this Tema
     */
    public double getRemuneracaoMensal() {
		return remuneracaoMensal;
	}


    /**
     * Sets title of Tema to a new title
     * 
     * @param novoTitulo String representing a new title for Tema
     */
	public void setTitulo(@NonNull String novoTitulo) {
		titulo = novoTitulo;
	}
	

    /**
     * Sets description of Tema to a new description
     * 
     * @param novaDescricao String representing a new description for Tema
     */
	public void setDescricao(@NonNull String novaDescricao) {
		descricao = novaDescricao;
	}
	
	public void setid (Long novoId) {
		
		id = novoId;
	}
	
	public void setMestradosCompativeis (List<Mestrado> mestrados) {
		mestradosCompativeis = mestrados;
	}
	
    
    /**
     * Function that adds a new Mestrado to mestradosCompativeis
     * 
     * @param mestrado Mestrado with Tema available
     * 
     * @return true if Mestrado was added successfully or false if it was not
     */
	public boolean addMestradoCompativel(Mestrado mestrado) {
		return mestradosCompativeis.add(mestrado);
	}
	

    /**
     * Function that removes a Mestrado from mestradosCompativeis
     * 
     * @param mestrado Mestrado that we want to remove
     * 
     * @return true if Mestrado was removed successfully or false if it was not
     */
	public boolean removeMestradoCompativel(Mestrado mestrado) {
		return mestradosCompativeis.remove(mestrado);
	}
	

    /**
     * Function that verifys if a Mestrado has Tema available
     * 
     * @param mestrado Mestrado to be verifyed
     * 
     * @return true if the mestrado has Tema available or false if it does not
     */
	public boolean isMestradoCompativel(Mestrado mestrado) {
		return mestradosCompativeis.contains(mestrado);
	}
	
    
    /**
     * Sets remuneracaoMensal of Tema to a new remuneracaoMensal
     * 
     * @param novaRemuneracao double representing the new remuneracaoMensal
     */
	public void setRemuneracaoMensal(double novaRemuneracao) {
		remuneracaoMensal = novaRemuneracao;
		
	}


    /**
     * Equals function for Tema
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
        var that = (Tema) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.titulo, that.titulo) &&
                Objects.equals(this.descricao, that.descricao) &&
                Objects.equals(this.mestradosCompativeis, that.mestradosCompativeis) &&
                Objects.equals(this.remuneracaoMensal, that.remuneracaoMensal);
    }



	/**
     * Hash code function for Tema
     * 
     * @return int representing the hashcode of Tema
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, mestradosCompativeis, remuneracaoMensal);
    }


    /**
     * To String for the Tema
     * 
     * @return String representing the Tema object and his attributes
     */
    @Override
    public String toString() {
        return "Tema[" +
                "id = " + id + ", " +
                "titulo = " + titulo + ", " +
                "descriçao = " + descricao + ", " +
                "mestrados compatíveis = " + toStringMestradosCompativeis()+ ", " +
                "remuneração mensal =" + remuneracaoMensal + ']';
    }


    /**
     * Function that converts mestradosCompativeis into a representative String
     * 
     * @return String representing mestradosCompativeis 
     */ 
    private String toStringMestradosCompativeis() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Mestrado mestrado : mestradosCompativeis) {
            sb.append(mestrado.getNome()).append(", ");
        }
        int index = sb.lastIndexOf(", ");
        sb.replace(index, index, " ]");
        return sb.toString();
    }

}