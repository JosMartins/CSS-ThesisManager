package pt.ul.fc.css.thesisman.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Empresa;


import java.util.List;

public interface EmpresaRepositorio extends CrudRepository<Empresa, Long> {

    @Query("SELECT e FROM Empresa e WHERE e.nome LIKE %:q%")
    List<Empresa> findByName(@Param("q") String q);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Empresa e WHERE e.nome LIKE %:q%")
    List<Empresa> findByNameAndLock(@Param("q") String q);
}
