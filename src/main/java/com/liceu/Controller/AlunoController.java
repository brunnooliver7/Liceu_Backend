package com.liceu.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;
import com.liceu.Service.AlunoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoService alunoService;

    @Autowired
    public Environment env;

    @GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.findOne(id);
            return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
        } catch (EntidadeNaoEncontradaException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return new ResponseEntity<Aluno>(alunoSalvo, HttpStatus.CREATED);
    }
    
    @PutMapping ("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoAtual = alunoRepository.findById(id);
        if (alunoAtual.isPresent()) {
            BeanUtils.copyProperties(aluno, alunoAtual.get(), "id");
            Aluno alunoSalvo = alunoRepository.save(alunoAtual.get());
            return ResponseEntity.ok(alunoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (Arrays.asList(env.getActiveProfiles()).contains("admin")) {
            try {
                alunoService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Seu perfil não tem permissão para executar este comando");
        }    
    }    

}