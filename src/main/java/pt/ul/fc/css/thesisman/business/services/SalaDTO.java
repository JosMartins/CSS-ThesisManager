package pt.ul.fc.css.thesisman.business.services;

import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.ElementCollection;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.datatypes.SlotTempo;
import pt.ul.fc.css.thesisman.entities.Sala;

@Component
public class SalaDTO {

	private Long id;
    private String designacao;
    private List<SlotTempo> ocupacao;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<SlotTempo> getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(List<SlotTempo> ocupacao) {
		this.ocupacao = ocupacao;
	}
}
