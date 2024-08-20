package pt.ul.fc.css.thesisman.entities;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import org.springframework.lang.NonNull;


/**
 * Subclass of Tese that represents ModalidadeProjeto
 * <p>
 * {@code orientadorEmpresarial} UtilizadorEmpresarial advising Aluno in this Thesys
 */
@Entity
@DiscriminatorValue("PROJETO")
public class ModalidadeProjeto extends Tese{
    
    @NonNull
    @ManyToOne
    private UtilizadorEmpresarial orientadorEmpresarial;


    /**
     * Empty constructor for the ModalidadeProjeto class
     * Creates a ModalidadeProjeto using the Tese Empty constructor
     */
    public ModalidadeProjeto(){
       super();
       orientadorEmpresarial = new UtilizadorEmpresarial();
    }


    /**
     * Constructor for the ModalidadeProjeto class
     * Creates a ModalidadeProjeto using the given parameters using the Tese constructor
     *
     * @param tema    Tema of this Thesys
     * @param aluno   Aluno researching this Thesys
     * @param docente Docente Teacher adviding Aluno in this Thesys
     * @param uE      UtilizadorEmpresarial advising Aluno in this Thesys
     */
    public ModalidadeProjeto(@NonNull Tema tema, @NonNull Aluno aluno, @NonNull Docente docente ,@NonNull UtilizadorEmpresarial uE){
        super(tema,aluno,docente);
        orientadorEmpresarial = uE;
    }
    

    /**
     * Gets the orientadorEmpresarial of ModalidadeProjeto
     * 
     * @return orientadorEmpresarial advising Aluno in this Thesys
     */
    @NonNull
    public UtilizadorEmpresarial getOrientadorEmpresarial(){
        return orientadorEmpresarial;
    }


    /**
     * Sets the orientadorEmpresarial to a new UtilizadorEmpresarial
     * 
     * @param novoUE UtilizadorEmpresarial new advisor for this Thesys
     */
    public void setOrientadorEmpresarial(UtilizadorEmpresarial novoUE) {
        orientadorEmpresarial = novoUE;
    }


    /**
     * To String for the ModalidadeProjeto
     * 
     * @return String representing the ModalidadeProjeto object and his attributes
     */
    @Override
    public String toString() {
        return "ModalidadeProjeto[" +
                "id = " + super.getId() + ", " +
                "tema = " + super.getTema() + ", " +
                "aluno = " + super.getAluno() + ", " +
                "docente = " + super.getOrientador() + 
                "orientadorEempresarial = " + orientadorEmpresarial + " ]";
    }



    /**
     * Hash code function for ModalidadeProjeto
     * 
     * @return int representing the hashcode of ModalidadeProjeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orientadorEmpresarial);
    }



    /**
     * Equals function for ModalidadeProjeto
     * 
     * @param obj Object that we want to compare this. to
     * 
     * @return true if both objects are equals or false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        ModalidadeProjeto that = (ModalidadeProjeto) obj;
    
        return  Objects.equals(getId(), that.getId()) &&
                getTema().equals(that.getTema()) &&
                getOrientador().equals(that.getOrientador()) &&
                getAluno().equals(that.getAluno()) &&
                orientadorEmpresarial.equals(that.orientadorEmpresarial);
    }

}
