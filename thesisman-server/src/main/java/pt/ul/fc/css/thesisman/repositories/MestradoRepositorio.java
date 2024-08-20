package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Mestrado;


import java.util.List;

public interface MestradoRepositorio extends JpaRepository<Mestrado, Long> {

    @Query("SELECT m FROM Mestrado m WHERE m.nome LIKE %:q%")
    List<Mestrado> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM Mestrado m WHERE m.nome LIKE %:q%")
    List<Mestrado> findByNameAndLock(@Param("q") String q);
}
