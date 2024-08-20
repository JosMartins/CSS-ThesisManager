package pt.ul.fc.css.thesisman;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.thesisman.entities.Administrador;
import pt.ul.fc.css.thesisman.entities.Docente;
import pt.ul.fc.css.thesisman.repositories.AdministradorRepositorio;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;
import pt.ul.fc.css.thesisman.repositories.DocenteRepositorio;
import pt.ul.fc.css.thesisman.entities.*;
import pt.ul.fc.css.thesisman.repositories.*;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ThesisManMVCApplication {

    private static final Logger log = LoggerFactory.getLogger(ThesisManMVCApplication.class);

    @Bean
    public CommandLineRunner demo(DocenteRepositorio docenteRep,
                                  UtilizadorEmpresarialRepositorio utilizadorEmpresarialRepositorio,
                                  EmpresaRepositorio empresaRepositorio,
                                  TemaRepositorio temaRepositorio,
                                  MestradoRepositorio mestradoRepositorio,
                                  AlunoRepositorio alunoRepositorio,
                                  AdministradorRepositorio adminRep,
                                  SalaRepositorio salaRep,
                                  TeseRepositorio teseRep,
                                  DefesaPropostaTeseRepositorio defesaPropostaTeseRep,
                                  DefesaTeseRepositorio defesaTeseRep
                                  ) {
        return args -> {
            log.info("Aplicacao Iniciada");

            ///Populacao da base de dados
            log.info("Populationg database!");
            //Administrador - 1
            adminRep.save(new Administrador("admin@fc.ul.pt", "admin"));
            log.debug("Admin save: {}", adminRep.findAll());

            //Alunos - 3
            alunoRepositorio.save(new Aluno("fc58228@alunos.fc.ul.pt", "jose"));
            alunoRepositorio.save(new Aluno("fc58243@alunos.fc.ul.pt", "maria"));
            alunoRepositorio.save(new Aluno("fc58174@alunos.fc.ul.pt", "vasco"));
            log.debug("Alunos save: {}", alunoRepositorio.findAll());

            //Doncetes - 4
            docenteRep.save(new Docente("docente1@docentes.fc.ul.pt", "docente1"));
            docenteRep.save(new Docente("docente2@docentes.fc.ul.pt", "docente2"));
            docenteRep.save(new Docente("docente3@docentes.fc.ul.pt", "docente3"));
            docenteRep.save(new Docente("docente4@docentes.fc.ul.pt", "docente4"));
            log.debug("Docentes save: {}", docenteRep.findAll());

            //Empresas - 3
            empresaRepositorio.save(new Empresa("Construcoes Lda", "Rua da Alegria"));
            empresaRepositorio.save(new Empresa("Worten", "CC Vasco da Gama"));
            empresaRepositorio.save(new Empresa("Hospital Santa Maria", "Cidade Universitaria"));
            log.debug("Empresas save: {}", empresaRepositorio.findAll());

            //Utilizadores Empresariais - 2
            utilizadorEmpresarialRepositorio.save(new UtilizadorEmpresarial("joao@mcdonalds.pt", "joao", empresaRepositorio.findById(1L).get())); //im sure that there is an empresa with that id, so I don't need the get.
            utilizadorEmpresarialRepositorio.save(new UtilizadorEmpresarial("rafael@burgerking.pt", "rafael", empresaRepositorio.findById(2L).get()));
            log.debug("Utilizadores Empresariais save: {}", utilizadorEmpresarialRepositorio.findAll());

            //Mestrados - 4
            mestradoRepositorio.save(new Mestrado("Engenharia Informática"));
            mestradoRepositorio.save(new Mestrado("Engenharia Biomédica"));
            mestradoRepositorio.save(new Mestrado("Engenharia de Materiais"));
            mestradoRepositorio.save(new Mestrado("Engenharia de Telecomunicações"));
            log.debug("Mestrados save: {}", mestradoRepositorio.findAll());

            //Temas - 8
            String descricaoT1 = "Fazer computadores e comunicacoes seguras.";
            List<Mestrado> mestrados1 = new ArrayList<>();
            mestrados1.add(mestradoRepositorio.findById(1L).get());
            mestrados1.add(mestradoRepositorio.findById(4L).get());
            temaRepositorio.save(new Tema("Seguraça", descricaoT1, mestrados1, 750.50));

            String descricaoT2 = "Aprender padroes de sofware e como os aplicar.";
            temaRepositorio.save(new Tema("Software", descricaoT2, mestrados1, 650.50));

            String descricaoT3 = "Aprender a fazer jogos.";
            List<Mestrado> mestrados3 = new ArrayList<>();
            mestrados3.add(mestradoRepositorio.findById(1L).get());
            temaRepositorio.save(new Tema("Jogos", descricaoT3, mestrados3, 500.50));

            String descricaoT4 = "Estudar formas de aplicar a tecnologia informatica na medicina.";
            List<Mestrado> mestrados4 = new ArrayList<>();
            mestrados4.add(mestradoRepositorio.findById(1L).get());
            mestrados4.add(mestradoRepositorio.findById(2L).get());
            temaRepositorio.save(new Tema("Medicina informatica", descricaoT4, mestrados4, 430.2));

            String descricaoT5 = "Estudar a composicao dos materias e como pode ser aplicada na engenharia e construcao.";
            List<Mestrado> mestrados5 = new ArrayList<>();
            mestrados5.add(mestradoRepositorio.findById(3L).get());
            temaRepositorio.save(new Tema("Materiais", descricaoT5, mestrados5, 201.4));

            String descricaoT6 = "Estudar a forma como se processa a comunicacao informatica atraves da rede.";
            List<Mestrado> mestrados6 = new ArrayList<>();
            mestrados6.add(mestradoRepositorio.findById(4L).get());
            temaRepositorio.save(new Tema("Redes", descricaoT6, mestrados6, 300.0));

            String descricaoT7 = "Estudar a forma como os materiais podem ser usados para criar dispositivos medicos.";
            List<Mestrado> mestrados7 = new ArrayList<>();
            mestrados7.add(mestradoRepositorio.findById(2L).get());
            mestrados7.add(mestradoRepositorio.findById(3L).get());
            temaRepositorio.save(new Tema("Dispositivos medicos", descricaoT7, mestrados7, 500.0));

            String descricaoT8 = "Estudar a forma como a psicologia humana afeta o desenvolvimento de software.";
            List<Mestrado> mestrados8 = new ArrayList<>();
            temaRepositorio.save(new Tema("Psicologia do software", descricaoT8, mestrados8, 100.0));

            log.debug("Temas save: {}", temaRepositorio.findAll());

            //Salas - 3
            Sala s1 =salaRep.save(new Sala("Sala 1.2.25"));
            Sala s2 = salaRep.save(new Sala("Sala 1.3.12"));
            salaRep.save(new Sala("Sala 6.3.12"));

            log.debug("Salas save: {}", salaRep.findAll());

            //Teses - 2
            Tema tt1 = temaRepositorio.findByName("Seguraça").get(0);
            Aluno at1 = alunoRepositorio.findByName("fc58228@alunos.fc.ul.pt").get(0);
            Docente dt1 = docenteRep.findByName("docente1@docentes.fc.ul.pt").get(0);
            Tese tsaved1 = teseRep.save(new ModalidadeDissertacao(tt1,at1,dt1));

            Tema tt2 = temaRepositorio.findByName("Software").get(0);
            Aluno at2 = alunoRepositorio.findByName("fc58243@alunos.fc.ul.pt").get(0);
            Docente dt2 = docenteRep.findByName("docente3@docentes.fc.ul.pt").get(0);
            UtilizadorEmpresarial ut2 = utilizadorEmpresarialRepositorio.findByName("joao@mcdonalds.pt").get(0);
            teseRep.save(new ModalidadeProjeto(tt2,at2,dt2, ut2));

            log.debug("Teses save: {}", teseRep.findAll());


            log.info("Database populated!");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ThesisManMVCApplication.class, args);
    }
}
