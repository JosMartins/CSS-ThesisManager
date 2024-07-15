package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Docente;

import java.util.List;

public interface DocenteRepositorio extends CrudRepository<Docente, Long> {
        @Query("SELECT d FROM Docente d WHERE d.username LIKE %:q%")
        List<Docente> findByName(@Param("q") String q);

        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("SELECT d FROM Docente d WHERE d.username LIKE %:q%")
        List<Docente> findByNameAndLock(@Param("q") String q);

}
