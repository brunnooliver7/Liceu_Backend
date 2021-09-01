package com.liceu.Service;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Professor;
import com.liceu.Repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor findOne(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format("Não existe cadastro de professor com código %d", id)));
    }

    public void delete(Long id) {
        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
			throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de professor com código %d", id));
        }
    }
    
}
