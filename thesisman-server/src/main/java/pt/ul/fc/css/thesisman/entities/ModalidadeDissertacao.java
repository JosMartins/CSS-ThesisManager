package pt.ul.fc.css.thesisman.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


/**
 * Subclass of Tese that represents ModalidadeDissertacao
 */
@Entity
@DiscriminatorValue("DISSERTACAO")
public class ModalidadeDissertacao extends Tese {
    
    /**
     * Empty constructor for the ModalidadeDissertacao class
     * Creates a ModalidadeDissertacao using the Tese Empty constructor
     */
    public ModalidadeDissertacao(){
       super();
    }
    
    
    /**
     * Constructor for the ModalidadeDissertacao class
     * Creates a ModalidadeDissertacao using the given parameters using the Tese constructor
     *
     * @param tema    Tema of this Thesys
     * @param aluno   Aluno researching this Thesys
     * @param docente Docente Teacher adviding Aluno in this Thesys
     */
    public ModalidadeDissertacao(@NonNull Tema tema, @NonNull Aluno aluno, @NonNull Docente docente ){
        super(tema,aluno,docente);
    }
    

    /**
     * To String for the ModalidadeDissertacao
     * 
     * @return String representing the ModalidadeDissertacao object and his attributes
     */
    @Override
    public String toString() {
        return "ModalidadeDissertacao[" +
                "id = " + super.getId() + ", " +
                "tema = " + super.getTema() + ", " +
                "aluno = " + super.getAluno() + ", " +
                "docente = " + super.getOrientador() + ']';
    }
    

}