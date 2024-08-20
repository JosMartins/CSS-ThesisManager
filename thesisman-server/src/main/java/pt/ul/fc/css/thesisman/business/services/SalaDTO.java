package pt.ul.fc.css.thesisman.business.services;

import java.util.List;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.datatypes.SlotTempo;

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
