package com.liceu.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Professor;
import com.liceu.Repository.ProfessorRepository;
import com.liceu.Service.ProfessorService;

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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    public ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService professorService;

    @Autowired
    public Environment env;

    @GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professores = professorRepository.findAll();
        return new ResponseEntity<List<Professor>>(professores, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Professor professor = professorService.findOne(id);
            return new ResponseEntity<Professor>(professor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        Professor professorSalvo = professorRepository.save(professor);
        return new ResponseEntity<Professor>(professorSalvo, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody Professor professor) {
        Optional<Professor> professorAtual = professorRepository.findById(id);
        if (professorAtual.isPresent()) {
            BeanUtils.copyProperties(professor, professorAtual.get(), "id", "sala", "dataCadastro");
            Professor professorSalvo = professorRepository.save(professorAtual.get());
            return ResponseEntity.ok(professorSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (Arrays.asList(env.getActiveProfiles()).contains("admin")) {
            try {
                professorService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Seu perfil não tem permissão para executar este comando");
        }    
    }

}