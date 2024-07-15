package pt.ul.fc.css.thesisman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.entities.DefesaPropostaTese;

public interface DefesaPropostaTeseRepositorio extends CrudRepository<DefesaPropostaTese, Long> {

	
}