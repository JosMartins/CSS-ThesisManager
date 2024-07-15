package pt.ul.fc.css.thesisman.entities;

import java.util.Objects;
import java.util.List;
import java.util.LinkedList;

import jakarta.persistence.*;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * Class representing a Thesys Submittion made by Aluno
 * <p>
 * {@code admin}         Admin in charge of the Thesys Submittion
 * {@code aluno}         Aluno who made the submittion
 * {@code listaTemas}    List of Tema with all Temas submitted by Aluno
 * {@code temaAtribuido} Tema selected for the Thesys
 * {@code tese}          Tese representing the Thesis subject in submittion
 */
@Entity
public class CandidaturaTese {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private Aluno aluno;

	@OneToMany
	private List<Tema> listaTemas;

	@OneToOne
	private Tema temaAtribuido;

	@Nullable
	@OneToOne
	private Tese tese;


	/**
	 * Empty constructor for the class CandidaturaTese
	 * Creates a CandidaturaTese object with all default attributes
	 */
	public CandidaturaTese() {
		aluno = new Aluno();
		listaTemas = new LinkedList<>();
		temaAtribuido = null;
	}


	/**
	 * Constructor for the class CandidaturaTese
	 * Creates a CandidaturaTese object with the given parameters
	 * temaAtribuido is set to null by default
	 *
	 * @param alunoCandidato          Aluno who made the submittion
	 * @param listaTemasCandidatura   List of Tema with all Temas submitted by Aluno
	 */
	public CandidaturaTese(@NonNull Aluno alunoCandidato, @NonNull List<Tema> listaTemasCandidatura) {

		aluno = alunoCandidato;
		listaTemas = listaTemasCandidatura;
		temaAtribuido = null;
	}


	/**
	 * Gets the aluno of CandidaturaTese
	 * 
	 * @return Aluno who made the submittion
	 */
	public Aluno getAluno() {
		return aluno;
	}


	/**
	 * Gets the of listaTemas CandidaturaTese
	 * 
	 * @return listaTemas List of Tema with all Temas submitted by Aluno
	 */
	public List<Tema> getListaTemas() {
		return listaTemas;
	}


	/**
	 * Gets temaAtribuido of CandidaturaTese
	 * 
	 * @return temaAtribuido Tema selected for the Thesys
	 */
	public Tema getTema() {
		return temaAtribuido;
	}


	/**
	 * Function that adds a Tema to listaTemas
	 * 
	 * @param tema Tema that we want to add to listaTemas
	 * 
	 * @return true if tema was added successfully or false if it was not
	 */
	public boolean addListaTemas(Tema tema) {
		if (listaTemas.size() < 5) {
			listaTemas.add(tema);
			return true;
		}
		return false;
	}


	/**
	 * Function that removes a Tema from listaTemas
	 * 
	 * @param tema Tema that we want to remove from listaTemas
	 * 
	 * @return true if tema was removed successfully or false if it was not
	 */
	public boolean removeListaTemas(Tema tema) {
		if (listaTemas.contains(tema)) {
			listaTemas.remove(tema);
			return true;
		}
		return false;
	}


	/**
	 * Sets the aluno of CandidaturaTese to a new Aluno
	 * 
	 * @param novoAluno Aluno representing the new Aluno
	 */
	public void setAluno(Aluno novoAluno) {
		aluno = novoAluno;
	}


	public Tema getTemaAtribuido() {
		return temaAtribuido;
	}


	/**
	 * Sets the temaAtribuido of CandidaturaTese to a new Tema
	 * 
	 * @param tema Tema representing the new tema selected
	 */
	public void setTemaAtribuido(Tema tema) {
		temaAtribuido = tema;
	}


	/**
	 * Gets the Thesys
	 *
	 * @return the Thesys
	 */
	@Nullable
	public Tese getTese() {
		return tese;
	}


	public void setTese(@Nullable Tese tese) {
		this.tese = tese;
	}

	/**
	 * Equals function for CandidaturaTese
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
		var that = (CandidaturaTese) obj;
		return this.aluno.equals(that.aluno) &&
				this.temaAtribuido.equals(that.temaAtribuido) &&
				Objects.equals(this.listaTemas, that.listaTemas);

	}


	/**
	 * Hash code function for CandidaturaTese
	 * 
	 * @return int representing the hashcode of Empresa
	 */
	@Override
	public int hashCode() {
		return Objects.hash(aluno, listaTemas, temaAtribuido);
	}


	/**
	 * To String for the CandidaturaTese
	 * 
	 * @return String representing the CandidaturaTese object and his attributes
	 */
	@Override
	public String toString() {
		return "Candidatura[" +
		"aluno = " + aluno.getUsername() + ", " +
		//"temaAtribuido = " + temaAtribuido.getTitulo() + ", " +
		"listaTemas = " + toStringTemas() + ']';
	}


	/**
	 * Function that converts listaTemas into a representative String
	 * 
	 * @return String representing listaTemas 
	 */ 
	private String toStringTemas() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (Tema t : listaTemas) {
			sb.append(t.getTitulo()).append(", ");
		}
		int index = sb.lastIndexOf(", ");
		sb.replace(index, index, " ]");
		return sb.toString();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setListaTemas(List<Tema> listaTemas) {
		this.listaTemas = listaTemas;
	}

}

