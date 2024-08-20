package pt.ul.fc.css.thesisman.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.AlunoDTO;
import pt.ul.fc.css.thesisman.business.services.CandidaturaTeseDTO;
import pt.ul.fc.css.thesisman.business.services.DefesaPropostaTeseDTO;
import pt.ul.fc.css.thesisman.business.services.DocenteDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.ApplicationException;
import pt.ul.fc.css.thesisman.datatypes.ModalidadeApresentacao;
import pt.ul.fc.css.thesisman.datatypes.SlotTempo;
import pt.ul.fc.css.thesisman.entities.*;
import pt.ul.fc.css.thesisman.repositories.AlunoRepositorio;
import pt.ul.fc.css.thesisman.repositories.DefesaPropostaTeseRepositorio;
import pt.ul.fc.css.thesisman.repositories.DocenteRepositorio;
import pt.ul.fc.css.thesisman.repositories.SalaRepositorio;

@Component
public class DefesaPropostaTeseService {

    @Autowired
    private AlunoRepositorio alunoRep;

    @Autowired
    private DocenteRepositorio docenteRep;

    @Autowired
    private SalaRepositorio salaRep;

    @Autowired
    private DefesaPropostaTeseRepositorio defesaRep;


    public List<DefesaPropostaTeseDTO> getDefesas() {
        ArrayList<DefesaPropostaTeseDTO> arr = new ArrayList<>();
        for (DefesaPropostaTese cand : defesaRep.findAll()) {
            DefesaPropostaTeseDTO dto = dtofy(cand);
            arr.add(dto);
        }
        return arr;
    }

    public void addDefesa(Double nota, ModalidadeApresentacao modalidadeApresentacao, AlunoDTO alunoDto,
                          DocenteDTO elementoJuri, String propostaTese) throws ApplicationException {

        DefesaPropostaTese defesaPropostaTese = new DefesaPropostaTese();

        if (nota.isNaN() || modalidadeApresentacao == null || alunoDto == null || elementoJuri == null || propostaTese.isEmpty()) {
            throw new ApplicationException("Defesa de tese mal formatada");
        }

        defesaPropostaTese.setNota(nota);
        Aluno al = null;
        for (Aluno aluno : alunoRep.findAll()) {
            if (aluno.getUsername().equals(alunoDto.getUsername())) {
                al = alunoRep.findById(aluno.getId()).get();
            }
        }

        Docente doc = null;
        for (Docente docente : docenteRep.findAll()) {
            if (docente.getUsername().equals(elementoJuri.getUsername())) {
                doc = docenteRep.findByName(docente.getUsername()).get(0);
            }
        }

        defesaPropostaTese.setElementoJuri(doc);
        defesaPropostaTese.setPropostaTese(propostaTese);
        defesaPropostaTese.setModalidadeApresentacao(modalidadeApresentacao);

        if (modalidadeApresentacao == ModalidadeApresentacao.REMOTO) {
            defesaPropostaTese.setDuracao(60);
        } else {
            defesaPropostaTese.setDuracao(90);
        }

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, defesaPropostaTese.getDuracao());
        if (modalidadeApresentacao == ModalidadeApresentacao.PRESENCIAL) {
            for (Sala sala : salaRep.findAll()) {
                if (sala.verificarDisponibilidade(start,end)) {
                    defesaPropostaTese.setSala(sala);
                    break;
                }
            }
            defesaPropostaTese.marcar(start, modalidadeApresentacao);
        }



        defesaRep.save(defesaPropostaTese);

    }

    public void setNota(Long id, Double nota) throws ApplicationException {
        if (nota > 20 || nota < 0) {
            throw new ApplicationException("Nota mal formatada");
        }

        Optional<DefesaPropostaTese> defesa = defesaRep.findById(id);
        if (defesa.isPresent()) {
            defesa.get().setNota(nota);
            defesaRep.save(defesa.get());
        }

    }

    private static DefesaPropostaTeseDTO dtofy(DefesaPropostaTese defesa) {
        DefesaPropostaTeseDTO dto = new DefesaPropostaTeseDTO();

        dto.setId(defesa.getId());
        dto.setNota(defesa.getNota());
        dto.setModalidadeApresentacao(defesa.getModalidadeApresentacao());
        dto.setDuracao(defesa.getDuracao());
        if (defesa.getSala() != null) {
            dto.setSala(SalaService.dtofy(defesa.getSala()));
        }
        dto.setElementoJuri(DocenteService.dtofy(defesa.getElementoJuri()));
        dto.setPropostaTese(defesa.getPropostaTese());

        return dto;
    }
}
