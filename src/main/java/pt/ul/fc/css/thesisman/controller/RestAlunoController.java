package pt.ul.fc.css.thesisman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.ApplicationException;
import pt.ul.fc.css.thesisman.helpers.LoginRequest;
import pt.ul.fc.css.thesisman.service.AlunoService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("api")
public class RestAlunoController {
    private static final SecureRandom rng = new SecureRandom();
    private static final String TOKEN_GEN_PASS = "css-thesisman";

    private List<String> tokens = new ArrayList<>();

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/alunos")
    public List<AlunoDTO> all() {
        return alunoService.getAlunos();
    }

    @GetMapping("/aluno/{id}")
    public Optional<AlunoDTO> all(@PathVariable Long id) {
        return alunoService.getAluno(id);
    }

    @PostMapping("/aluno/login")
    public ResponseEntity<?> login(@NonNull @RequestBody LoginRequest loginReq) {

        try {
            String username = loginReq.getUsername();
            String password = loginReq.getPassword();
            Optional<AlunoDTO> aluno = alunoService.login(username, password);
            String token = makeToken(username);

            if (aluno.isPresent() && aluno.get().isLogin()) {
                return ResponseEntity.ok(Map.of("aluno", aluno.get(), "token", token));
            } else {
                return aluno.isPresent() ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.notFound().build();
            }
        } catch (ApplicationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/aluno/logout/")
    public ResponseEntity<Object> logout(@RequestBody AlunoDTO alunodto) {
    	try {
    		
            Optional<AlunoDTO> aluno = alunoService.logout(alunodto);
            if (aluno.isPresent() && !aluno.get().isLogin()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/aluno/logout")
    public ResponseEntity<?> logout(@NonNull @RequestBody LoginRequest loginReq) {
        String username = loginReq.getUsername();
        alunoService.logout(username);
        return ResponseEntity.ok().build();
    }


    private String makeToken(String username) {
        String token = "1";

        return "aijlesf";
    }


}
