package com.liceu.Controller;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;
import com.liceu.Service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Validated @RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(alunoSalvo);
    }

}