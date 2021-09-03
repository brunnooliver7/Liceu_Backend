package com.liceu.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;
import com.liceu.Model.Aula;
import com.liceu.Repository.AlunoRepository;
import com.liceu.Repository.AulaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping ("/aluno/por-nome")
    public List<Aluno> listarAlunosPorNome(String nome) {
        return alunoRepository.findAllByNomeContaining(nome);
    }

    @GetMapping ("aluno/por-nome-unico")
    public Optional<Aluno> listarAlunoPorNomeUnico(String nome) {
        return alunoRepository.findByNome(nome);
    }

    @GetMapping ("aula/por-dia-entre")
    public List<Aula> listarAulasPorDataEntre(
        @DateTimeFormat(pattern="ddMMyyyy") Date dataInicial, 
        @DateTimeFormat(pattern="ddMMyyyy") Date dataFinal
    ) {
        return aulaRepository.findByDiaBetween(dataInicial, dataFinal);
    }

}
