package pt.ul.fc.css.thesisman.service;

import java.security.SecureRandom;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.CandidaturaTeseDTO;
import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.entities.CandidaturaTese;
import pt.ul.fc.css.thesisman.entities.Tema;
import pt.ul.fc.css.thesisman.handlers.CandidaturaHandler;
import pt.ul.fc.css.thesisman.repositories.CandidaturaTeseRepositorio;
import pt.ul.fc.css.thesisman.repositories.TemaRepositorio;

@Component
public class CandidaturaTeseService {

    @Autowired
    private CandidaturaTeseRepositorio candRep;

    @Autowired
    private TemaRepositorio temaRep;

    @Autowired
    private CandidaturaHandler candHandler;

    @Autowired
    private TemaService temaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AdministradorService administradorService;

    public List<CandidaturaTeseDTO> getCandidaturas() {
        ArrayList<CandidaturaTeseDTO> arr = new ArrayList<>();
        for (CandidaturaTese cand : candRep.findAll()) {
            CandidaturaTeseDTO dto = dtofy(cand);
            arr.add(dto);
        }
        return arr;
    }

    public Optional<CandidaturaTeseDTO> getCandidatura(Long id) {
        return candRep.findById(id).map(CandidaturaTeseService::dtofy);
    }
    public Optional<CandidaturaTeseDTO> criarCandidatura(CandidaturaTeseDTO newCand) throws EmptyFieldException {

        if (newCand.getListaTemas().size() > 5) {
            throw new EmptyFieldException("Lista de temas não pode ter mais de 5 temas");

        }

        Optional<AlunoDTO> al = alunoService.getAluno(newCand.getAluno().getId());

        if (al.isEmpty()) {
            throw new EmptyFieldException("Aluno não encontrado");
        }

        List<Tema> temas = new ArrayList<>();
        for (TemaDTO tema : newCand.getListaTemas()) {
            Optional<Tema> t = temaRep.findById(tema.getId());

            if (t.isEmpty()) {
                throw new EmptyFieldException("Tema não encontrado");
            }
            temas.add(t.get());
        }

        CandidaturaTese cand = candHandler.submeterCandidatura(temas, al.get());
        SecureRandom rand = new SecureRandom();
        int randomIndex = rand.nextInt(temas.size() + 1);

        if (randomIndex == 6) {
            administradorService.adicionarCandidatura(cand);
        } else {
            cand.setTemaAtribuido(temas.get(randomIndex));
        }


        return cand == null ? Optional.empty() : Optional.of(dtofy(cand));
    }


    public boolean cancelCandidatura(CandidaturaTeseDTO newCand) throws EmptyFieldException {
    	
		if (newCand.getListaTemas().size() > 5 || newCand.getListaTemas().isEmpty()) {
			throw new EmptyFieldException("Lista de temas mal formatada");
		}

		Optional<CandidaturaTese> t = candRep.findById(newCand.getId());

		if (t.isPresent()) {
			List<Tema> temas = new ArrayList<>();

			for (TemaDTO tema : newCand.getListaTemas()) {
				try {
					temas.add(temaRep.findById(tema.getId()).get());
				} catch (NoSuchElementException e) {
					return false;
				}
			}
			t.get().setListaTemas(temas);
			candRep.save(t.get());
			return true;
		}

		return false;
	}


	public boolean setTemaCandidatura(CandidaturaTeseDTO newCand, Long id) throws EmptyFieldException {

		Optional<CandidaturaTese> cand = candRep.findById(newCand.getId());
		Optional<Tema> tema = temaRep.findById(id);
		if (cand.isPresent() && tema.isPresent()) {

			cand.get().setTemaAtribuido(tema.get());
			candRep.save(cand.get());
			return true;
		}

		return false;
	}

         static CandidaturaTeseDTO dtofy (CandidaturaTese c){
            CandidaturaTeseDTO dto = new CandidaturaTeseDTO();
            dto.setId(c.getId());
            dto.setTese(c.getTese());
            dto.setAluno(AlunoService.dtofy(c.getAluno()));
            dto.setListaTemas(c.getListaTemas().stream().map(TemaService::dtofy).toList());

            return dto;
        }
    }
