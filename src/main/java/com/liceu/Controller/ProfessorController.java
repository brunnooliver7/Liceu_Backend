package com.liceu.Controller;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Professor;
import com.liceu.Repository.ProfessorRepository;
import com.liceu.Service.ProfessorService;

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
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    public ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService professorService;

    @GetMapping
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Professor> findOne(@PathVariable Long codigo) {

        Optional<Professor> professor = professorRepository.findById(codigo);
       
        if (professor.isPresent()) {
            return new ResponseEntity<>(professor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        Professor professorSalvo = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        professorRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long codigo, @Validated @RequestBody Professor professor) {
        Professor professorSalvo = professorService.atualizar(codigo, professor);
        return ResponseEntity.ok(professorSalvo);
    }

}