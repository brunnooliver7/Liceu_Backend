package com.liceu.Controller;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aula;
import com.liceu.Repository.AulaRepository;
import com.liceu.Service.AulaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/aulas")
public class AulaController {
    
    @Autowired
    public AulaRepository aulaRepository;

    @Autowired
    public AulaService aulaService;

    @GetMapping
    public List<Aula> findAll() {
        return aulaRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Aula> findOne(@PathVariable Long codigo) {

        Optional<Aula> aula = aulaRepository.findById(codigo);
       
        if (aula.isPresent()) {
            return new ResponseEntity<>(aula.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Aula> save(@RequestBody Aula aula) {        
        Aula aulaSalva = new Aula();
        aulaSalva.setProfessor(aula.getProfessor());
        aulaSalva.setDia(aula.getDia());
        aulaSalva.setAlunos(aula.getAlunos());
        aulaSalva = aulaRepository.save(aulaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaSalva);
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        aulaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Aula> atualizar(@PathVariable Long codigo, @Validated @RequestBody Aula aula) {
        Aula aulaSalvo = aulaService.atualizar(codigo, aula);
        return ResponseEntity.ok(aulaSalvo);
    }
    
}