package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Sala;


import java.util.List;

public interface SalaRepositorio extends JpaRepository<Sala, Long> {

    @Query("SELECT s FROM Sala s WHERE s.designacao LIKE %:q%")
    List<Sala> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Sala s WHERE s.designacao LIKE %:q%")
    List<Sala> findByNameAndLock(@Param("q") String q);
}
