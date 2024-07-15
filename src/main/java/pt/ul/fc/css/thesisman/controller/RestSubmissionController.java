package pt.ul.fc.css.thesisman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.service.SubmissionService;

@RestController()
@RequestMapping("api/submission")
public class RestSubmissionController {
	
	@Autowired
    private SubmissionService submissionService;

	@PostMapping("/propostatese")
	public ResponseEntity<Void> submeterProposta(@RequestBody AlunoDTO aluno){
		if(submissionService.submitProposta(aluno)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/tese")
	public ResponseEntity<Void> submeterTese(@RequestBody AlunoDTO aluno){
		
		if(submissionService.submitTese(aluno)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}



