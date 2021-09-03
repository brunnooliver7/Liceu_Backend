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

    @GetMapping ("/aluno/todos-nome")
    public List<Aluno> listarTodosAlunosPorNome(String nome) {
        return alunoRepository.findAllByNome(nome);
    }

    @GetMapping ("/aluno/nome-contem")
    public List<Aluno> listarAlunosPorNomeContem(String nome) {
        return alunoRepository.findAllByNomeContaining(nome);
    }

    @GetMapping ("/aluno/nome-exato")
    public Optional<Aluno> listarAlunoPorNomeExato(String nome) {
        return alunoRepository.findByNome(nome);
    }

    @GetMapping ("/aluno/primeiro-aluno-nome")
    public Optional<Aluno> listarPrimeiroAlunoPorNome(String nome) {
        return alunoRepository.findFirstByNome(nome);
    }

    @GetMapping ("/aluno/primeiro-nome-contem")
    public Optional<Aluno> listarPrimeiroAlunoPorNomeQueContem(String nome) {
        return alunoRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping ("/aluno/existe")
    public Optional<Aluno> listarAlunoExiste(String nome) {
        return alunoRepository.existsByNome(nome);
    }

    @GetMapping ("/aluno/contar-nome")
    public int contarPorNome(String nome) {
        return alunoRepository.countByNome(nome);
    }

    @GetMapping ("/aluno/contar-nome-contem")
    public int contarPorNomeQueContem(String nome) {
        return alunoRepository.countByNomeContaining(nome);
    }

    @GetMapping ("aula/dia-entre")
    public List<Aula> listarAulasPorDataEntre(
        @DateTimeFormat(pattern="ddMMyyyy") Date dataInicial, 
        @DateTimeFormat(pattern="ddMMyyyy") Date dataFinal
    ) {
        return aulaRepository.findByDiaBetween(dataInicial, dataFinal);
    }

    @GetMapping ("/aluno/query-nome-exato")
    public Aluno consultarPorNomeExatoXml(String nome) {
        return alunoRepository.consultarPorNome(nome);
    }

    @GetMapping ("/aluno/query-nome-contem")
    public List<Aluno> consultarPorNomeExatoRepositoryImpl(String nome) {
        return alunoRepository.findNomeContem(nome);
    }
}
