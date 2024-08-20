package pt.ul.fc.css.thesisman.entities;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import org.springframework.lang.NonNull;


/**
 * This abstract class represents a Thesys that Aluno is researching
 * It represents a table with all Teses
 * <p>
 * {@code id}         Long representing the id of Tese in the table
 * {@code tema}       Tema of this Thesis
 * {@code aluno}      Aluno researching this Thesis
 * {@code orientador} Docente Teacher adviding Aluno in this Thesis
 * {@code document}   String representing the document submitted to apply for a Thesis
 */
@Entity
@Table(name = "Teses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MODALIDADE")
public abstract class Tese {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @OneToOne
    private Tema tema;

    @NonNull
    @OneToOne
    private Aluno aluno;

    @NonNull
    @OneToOne
    private Docente orientador;



    @Nullable
    @OneToMany
    private List<DefesaPropostaTese> defesaPropostasTese;



    @Nullable
    @OneToOne
    private DefesaTese defesaTese;


    /**
     * Empty constructor for the class Tese
     * Uses the Empty constructores of Tema, Aluno and Docente
     */
    protected Tese(){
        tema = new Tema();
        aluno = new Aluno();
        orientador = new Docente();
        defesaPropostasTese = new LinkedList<>();
        defesaTese = null;
    }

    
    /**
     * Constructor for the Tese class
     * Creates a Tese object with given parameters
     * document set with null value
     * 
     * @param temaTese       Tema of this Thesys
     * @param alunoTese      Aluno researching this Thesys
     * @param orientadorTese Docente Teacher adviding Aluno in this Thesys
     */
    protected Tese(@NonNull Tema temaTese, @NonNull Aluno alunoTese, @NonNull Docente orientadorTese){
        tema = temaTese;
        aluno = alunoTese;
        orientador = orientadorTese;
        defesaPropostasTese = new LinkedList<>();
        defesaTese = null;
    }



    /**
     * Gets the id of Tese in the table
     * 
     * @return id Long representing the id of Tese in the table
     */
    public Long getId() {
        return id;
    }



    /**
     * Gets the Tema of this Tese
     * 
     * @return tema Tema of this Thesys
     */
    @NonNull
    public Tema getTema(){
        return tema;
    }
    

    /**
     * Gets the Aluno of this Tese
     * 
     * @return aluno Aluno researching this Thesys
     */
    @NonNull
    public Aluno getAluno(){
        return aluno;
    }


    /**
     * Gets the Docente of this Tese
     * 
     * @return orientador Docente Teacher adviding Aluno in this Thesys
     */
    @NonNull
    public Docente getOrientador(){
        return orientador;
    }


    /**
     * Sets the tema of Tese to a new Tema
     * 
     * @param novoTema Tema new tema for Tese 
     */
    public void setTema(@NonNull Tema novoTema){
       tema = novoTema;
    }


    /**
     * Sets the aluno of Tese to a new Aluno
     *  
     * @param novoAluno Aluno new aluno for Tese
     */
    public void setAluno(@NonNull Aluno novoAluno){
        aluno = novoAluno;
    }
    

    /**
     * Sets the orientador of Tese to a new Docente
     * 
     * @param novoOrientador Docente new teacher for Tese
     */
    public void setOrientador(@NonNull Docente novoOrientador){
        orientador = novoOrientador;
    }


    /**
     * Equals function for Tese
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
        var that = (Tese) obj;
        return Objects.equals(this.id, that.id) &&
                this.tema.equals(that.tema) &&
                this.orientador.equals(that.orientador) &&
                this.aluno.equals(that.aluno);
    }



    /**
     * Hash code function for Tese
     * 
     * @return int representing the hashcode of Tese
     */
    @Override
    public int hashCode() {
        return Objects.hash(aluno, orientador, tema, id);
    }


    /**
     * Getter for the List of the Defenses of the Thesys Propositions
     *
     * @return the list containing the Defenses of thesis propositions
     */
    public List<DefesaPropostaTese> getDefesaPropostasTese() {
        return defesaPropostasTese;
    }


    /**
     * Setter for the List of the Defenses of the THesys Propositions
     *
     * @param defesaPropostasTese The list to set
     */
    public void setDefesaPropostasTese(List<DefesaPropostaTese> defesaPropostasTese) {
        this.defesaPropostasTese = defesaPropostasTese;
    }

    /**
     * Function that adds a Defense of Thesys propositions
     *
     * @param dpt the Defense of Thesis Propositions to Add
     */
    public void addDefesaPropostaTese(DefesaPropostaTese dpt) {
        defesaPropostasTese.add(dpt);
    }


    /**
     * Getter for the Thesys Defense
     *
     * @return The Thesys defense
     */
    public DefesaTese getDefesaTese() {
        return defesaTese;
    }


    /**
     * Setter for the Thesys Defense
     * @param defesaTese the Thesys Defense to set
     */
    public void setDefesaTese(DefesaTese defesaTese) {
        this.defesaTese = defesaTese;
    }


	public void setId(Long id) {
		this.id = id;
	}

}


