package pt.ul.fc.css.thesisman.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.MestradoDTO;
import pt.ul.fc.css.thesisman.entities.*;
import pt.ul.fc.css.thesisman.repositories.MestradoRepositorio;
import pt.ul.fc.css.thesisman.repositories.TemaRepositorio;

@Component
public class TemasTesesHandler extends Handler{

	@Autowired
	private TemaRepositorio catalogoTemas;

	@Autowired
	private MestradoRepositorio catalogoMestrados;

	
	public Tema submitTema(String nome, String desc, List<MestradoDTO> mestrados, double remMensal) { //Casos D,E
		Tema tema = new Tema();
		tema.setTitulo(nome);
		tema.setDescricao(desc);
		tema.setRemuneracaoMensal(remMensal);
		List<Mestrado> mIds = catalogoMestrados.findAllById(mestrados.stream().map(MestradoDTO::getId).toList());
		tema.setMestradosCompativeis(mIds);
		catalogoTemas.save(tema);

		return tema;
	}
	
	
	public List<Tema> getTemasList () { //Caso F
		
		return catalogoTemas.findAll();
		
	}

}
