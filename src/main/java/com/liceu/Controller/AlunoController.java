package com.liceu.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;
import com.liceu.Service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    public AlunoRepository alunoRepository;

    @Autowired
    public AlunoService alunoService;

    @Autowired
    public Environment env;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return new ResponseEntity<List<Aluno>>(alunoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Aluno> findOne(@PathVariable Long id) {

        Optional<Aluno> aluno = alunoRepository.findById(id);
        
        if (aluno.isPresent()) {
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (Arrays.asList(env.getActiveProfiles()).contains("admin")) {
            alunoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Seu perfil não tem permissão para executar este comando");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Validated @RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(alunoSalvo);
    }

}