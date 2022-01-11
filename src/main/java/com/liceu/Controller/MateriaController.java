package com.liceu.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Materia;
import com.liceu.Repository.MateriaRepository;
import com.liceu.Service.MateriaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    public MateriaRepository materiaRepository;

    @Autowired
    public MateriaService materiaService;

    @Autowired
    public Environment env;

    @GetMapping
    public ResponseEntity<List<Materia>> findAll() {
        List<Materia> materias = materiaRepository.findAll();
        return new ResponseEntity<List<Materia>>(materias, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Materia materia = materiaService.findOne(id);
            return new ResponseEntity<Materia>(materia, HttpStatus.OK);
        } catch (EntidadeNaoEncontradaException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Materia> save(@RequestBody Materia materia) {
        Materia materiaSalva = materiaRepository.save(materia);
        return new ResponseEntity<Materia>(materiaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Long id, @RequestBody Materia materia) {
        Optional<Materia> materiaAtual = materiaRepository.findById(id);
        if (materiaAtual.isPresent()) {
            BeanUtils.copyProperties(materia, materiaAtual.get(), "id", "professor");
            Materia materiaSalva = materiaRepository.save(materiaAtual.get());
            return ResponseEntity.ok(materiaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (Arrays.asList(env.getActiveProfiles()).contains("admin")) {
            try {
                materiaService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Matéria deletado com sucesso");
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Seu perfil não tem permissão para executar este comando");
        }
    }

}
