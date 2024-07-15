package pt.ul.fc.css.thesisman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.thesisman.business.services.UtilizadorEmpresarialDTO;
import pt.ul.fc.css.thesisman.service.UtilizadorEmpresarialService;

@RestController()
@RequestMapping("api")
public class RestUtilizadorEmpresarialController {

    @Autowired
    private UtilizadorEmpresarialService uEmpService;

    @GetMapping("/utilizadores-empresariais")
    List<UtilizadorEmpresarialDTO> all() {
        return uEmpService.getUtilEmp();
    }

    @GetMapping("/utilizador-empresarial/{id}")
    Optional<UtilizadorEmpresarialDTO> all(@PathVariable Long id) {
        return uEmpService.getUtilEmp(id);
    }


    @PostMapping("/utilizadores-empresariais/registar")
    public ResponseEntity<String> registaUtilizadorEmpresarial(@RequestBody UtilizadorEmpresarialDTO utilizadorEmpresarialDTO) {
        try {
            uEmpService.registaUtilizadorEmpresarial(utilizadorEmpresarialDTO.getUsername(), utilizadorEmpresarialDTO.getPassword(), utilizadorEmpresarialDTO.getNomeEmpresa());
            return new ResponseEntity<>("Utilizador empresarial registado com sucesso.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao registar utilizador empresarial: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/utilizadores-empresariais/login")
    public ResponseEntity<String> loginUtilizadorEmpresarial(@RequestBody UtilizadorEmpresarialDTO utilizadorEmpresarialDTO) {
    	try {
    		uEmpService.loginUtilizadorEmpresarial(utilizadorEmpresarialDTO.getUsername(), utilizadorEmpresarialDTO.getPassword());
    		return new ResponseEntity<>("Login bem sucedido.", HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Login falhado: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }
}