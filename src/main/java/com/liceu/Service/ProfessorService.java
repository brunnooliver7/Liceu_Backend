package com.liceu.Service;

import com.liceu.Model.Professor;
import com.liceu.Repository.ProfessorRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor buscarProfessorPeloCodigo(Long id) {
        Professor professorSalvo = professorRepository.getOne(id);
        if (professorSalvo == null) {
            throw new EmptyResultDataAccessException("Professor não encontrado", 1);
        }
        return professorSalvo;
    }

    public Professor atualizar(Long id, Professor professor) {
        Professor professorSalvo = buscarProfessorPeloCodigo(id);
        BeanUtils.copyProperties(professor, professorSalvo, "id");
        return professorRepository.save(professorSalvo);
    }

}