package pt.ul.fc.css.thesisman.business.services;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.datatypes.SlotTempo;
import pt.ul.fc.css.thesisman.entities.Docente;

import java.util.List;

@Component
public class DocenteDTO {

    private Long id;
    private String username;
    private String password;
    private boolean login;
    private List<SlotTempo> ocupacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String novoUsername) {
        this.username = novoUsername;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean newLogin) {
        login = newLogin;
    }

    public List<SlotTempo> getOccupacao() {
        return this.ocupacao;
    }

   public void setOccupacao(List<SlotTempo> newOcupacao) {
        ocupacao = newOcupacao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }

}
