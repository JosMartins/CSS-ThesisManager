package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Aluno;

import java.util.List;

public interface AlunoRepositorio extends CrudRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.username LIKE %:q%")
    List<Aluno> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Aluno a WHERE a.username LIKE %:q%")
    List<Aluno> findByNameAndLock(@Param("q") String q);

}