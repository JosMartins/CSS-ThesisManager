package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.thesisman.entities.Tema;

import java.util.List;

public interface TemaRepositorio extends JpaRepository<Tema, Long> {

    @Query("SELECT t FROM Tema t WHERE t.titulo LIKE %:q%")
    List<Tema> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM Tema t WHERE t.titulo LIKE %:q%")
    List<Tema> findByNameAndLock(@Param("q") String q);
}
