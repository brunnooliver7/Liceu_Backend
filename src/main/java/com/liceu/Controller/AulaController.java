package com.liceu.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Aula;
import com.liceu.Repository.AulaRepository;
import com.liceu.Service.AulaService;

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
@RequestMapping("/aula")
public class AulaController {
    
    @Autowired
    public AulaRepository aulaRepository;

    @Autowired
    public AulaService aulaService;

    @Autowired
    public Environment env;

    @GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> aulas = aulaRepository.findAll();
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Aula aula = aulaService.findOne(id);
            return new ResponseEntity<Aula>(aula, HttpStatus.OK);
        } catch (EntidadeNaoEncontradaException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Aula> save(@RequestBody Aula aula) {        
        Aula aulaSalva = aulaRepository.save(aula);
        return new ResponseEntity<Aula>(aulaSalva, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Aula> update(@PathVariable Long id, @RequestBody Aula aula) {
        Optional<Aula> aulaAtual = aulaRepository.findById(id);
        if (aulaAtual.isPresent()) {
            BeanUtils.copyProperties(aula, aulaAtual.get(), "id");
            Aula aulaSalva = aulaRepository.save(aulaAtual.get());
            return ResponseEntity.ok(aulaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (Arrays.asList(env.getActiveProfiles()).contains("admin")) {
            try {
                aulaService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Aula deletado com sucesso");
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Seu perfil não tem permissão para executar este comando");
        }    
    }
    
}