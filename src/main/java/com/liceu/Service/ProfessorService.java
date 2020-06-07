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

    public Professor buscarProfessorPeloCodigo(Long codigo) {
        Professor professorSalvo = professorRepository.getOne(codigo);
        if (professorSalvo == null) {
            throw new EmptyResultDataAccessException("Professor não encontrado", 1);
        }
        return professorSalvo;
    }

    public Professor atualizar(Long codigo, Professor professor) {
        Professor professorSalvo = buscarProfessorPeloCodigo(codigo);
        BeanUtils.copyProperties(professor, professorSalvo, "codigo");
        return professorRepository.save(professorSalvo);
    }

}