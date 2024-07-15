package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.UtilizadorEmpresarial;

import java.util.List;

public interface UtilizadorEmpresarialRepositorio extends CrudRepository<UtilizadorEmpresarial, Long> {

    @Query("SELECT u FROM UtilizadorEmpresarial u WHERE u.username LIKE %:q%")
    List<UtilizadorEmpresarial> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM UtilizadorEmpresarial u WHERE u.username LIKE %:q%")
    List<UtilizadorEmpresarial> findByNameAndLock(@Param("q") String q);
}
