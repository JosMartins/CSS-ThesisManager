package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Administrador;

import java.util.List;

public interface AdministradorRepositorio extends CrudRepository<Administrador, Long> {

    @Query("SELECT a FROM Administrador a WHERE a.username LIKE %:q%")
    List<Administrador> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Administrador a WHERE a.username LIKE %:q%")
    List<Administrador> findByNameAndLock(@Param("q") String q);
}
