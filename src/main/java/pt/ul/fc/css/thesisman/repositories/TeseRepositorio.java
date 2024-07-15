package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Tese;


import java.util.List;


public interface TeseRepositorio extends JpaRepository<Tese, Long> {

    @Query("SELECT t FROM Tese t WHERE t.tema.titulo LIKE %:q%")
    List<Tese> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM Tese t WHERE t.tema.titulo LIKE %:q%")
    List<Tese> findByNameAndLock(@Param("q") String q);
}
