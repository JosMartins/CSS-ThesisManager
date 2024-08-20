package pt.ul.fc.css.thesisman.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

import org.springframework.lang.NonNull;


/**
 * Class representing a Mestrado that Aluno can belong to
 * It represents a table with all Mestrado
 * <p>
 * {@code id}      Long representing a specific id of each Mestrado in the table
 * {@code nome}    String representing the name of Mestrado
 */
@Entity
@Table(name = "Mestrados")
public class Mestrado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NonNull
	private String nome;

	@OneToMany(fetch = FetchType.EAGER)
	@Column(name = "ALUNOS_INSCRITOS")
	private List<Aluno> alunosInscritos;

	@ManyToOne
	private Tema tema;

    /**
     * Empty constructor for the class Mestrado
     * Creates a Mestrado object with default name
     */
	public Mestrado() {
		nome = "";
		alunosInscritos = new ArrayList<>();
	}


	/**
     * Empty constructor for the class Mestrado
     * Creates a Mestrado object with a specific given name
     * 
     * @param nomeMestrado String representing the name of Mestrado
     */
	public Mestrado(@NonNull String nomeMestrado) {
		nome = nomeMestrado;
		alunosInscritos = new ArrayList<>();
	}


    /**
     * Gets the id of Mestrado in the table
     *
     * @return id Long representing the id of Mestrado in the table
     */
	public Long getId() {
		return id;
	}
	


    /**
     * Gets the name of Mestrado in the table
     * 
     * @return nome String represeting the name of Mestrado
     */
	@NonNull
	public String getNome() {
		return nome;
	}
	

	/**
	 * Sets the name of Mestrado to a new name
	 * 
	 * @param novoNome String representing a new name for Mestrado
	 */
	public void setNome(@NonNull String novoNome) {
		nome = novoNome;
	}

    
    /**
     * Gets alunosInscritos of Mestrado
     * 
     * @return List of Alunos who belong to this Mestrado
     */
	public List<Aluno> getAlunosInscritos() {
		return alunosInscritos;
	}


	public void addAlunoInscrito(Aluno a) {
		alunosInscritos.add(a);
	}
	
	public void setAlunosInscritosIds(List<Aluno> alunos) {
		alunosInscritos = alunos;
	}

	/**
     * Equals function for Mestado
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
        var that = (Mestrado) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.nome, that.nome);
    }


	/**
     * Hash code function for Mestrado
     * 
     * @return int representing the hashcode of Mestrado
     */
	@Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }


    /**
     * To String for the Mestrado
     * 
     * @return String representing the Mestrado object and his attributes
     */
	@Override
    public String toString() {
        return "Mestrado[" +
                "id = " + id + ", " +
                "nome =" + nome + ']';
    }


	public void setId(Long newId) {
		id = newId;
		
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
}
