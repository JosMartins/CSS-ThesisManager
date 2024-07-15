package pt.ul.fc.css.thesisman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.entities.Utilizador;


import java.util.List;

public interface UtilizadorRepositorio extends JpaRepository<Utilizador,Long> {

    @Query("SELECT u FROM Utilizador u WHERE u.username LIKE %:q%")
    List<Utilizador> findByName(@Param("q") String q);

}
