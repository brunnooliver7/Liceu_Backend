package com.liceu.Controller;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping ("/aluno/por-nome")
    public List<Aluno> listarAlunosPorNome(String nome) {
        return alunoRepository.findAllByNome(nome);
    }

    @GetMapping ("aluno/por-nome-unico")
    public Optional<Aluno> listarAlunoPorNomeUnico(String nome) {
        return alunoRepository.findByNome(nome);
    }

}
