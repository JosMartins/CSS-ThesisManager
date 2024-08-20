package pt.ul.fc.css.thesisman.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.CandidaturaTeseDTO;
import pt.ul.fc.css.thesisman.business.services.TemaDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.EmptyFieldException;
import pt.ul.fc.css.thesisman.service.AlunoService;
import pt.ul.fc.css.thesisman.service.CandidaturaTeseService;
import pt.ul.fc.css.thesisman.service.TemaService;

@RestController()
@RequestMapping("api")
public class RestCandidaturaTeseController {

	Logger logger = LoggerFactory.getLogger(RestCandidaturaTeseController.class);
	@Autowired
	private CandidaturaTeseService candTeseService;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private TemaService temaService;
	
	@GetMapping("/candidaturatese")
    public List<CandidaturaTeseDTO> all() {
        return candTeseService.getCandidaturas();
    }
	
	@PostMapping("/candidaturatese/candidatar")
	public ResponseEntity<?> createCandidatura(@RequestBody String newCand) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			CandidaturaTeseDTO jsonInput = objectMapper.readValue(newCand, CandidaturaTeseDTO.class);

			Optional<AlunoDTO> aluno = alunoService.getAluno(jsonInput.getAluno().getId());

			if (aluno.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			for (TemaDTO tema : jsonInput.getListaTemas()) {
				if (temaService.getTema(tema.getId()).isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
			}

			Optional<CandidaturaTeseDTO> candTest = candTeseService.criarCandidatura(jsonInput);

			if (candTest.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(objectMapper.writeValueAsString(candTest.get()));
			}


        } catch (Exception e) {
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
	
	@PostMapping("/candidaturatese/cancelar")
	public ResponseEntity<Void> cancelTema(@RequestBody CandidaturaTeseDTO newCand){
		//TODO mudar isto para verificar erros de user
		try {
			boolean isCanceled = candTeseService.cancelCandidatura(newCand);
			 return ResponseEntity.status(isCanceled ? HttpStatus.ACCEPTED : HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (EmptyFieldException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}

