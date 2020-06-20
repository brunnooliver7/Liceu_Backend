package com.liceu.Service;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno buscarAlunoPeloCodigo(Long id) {
        Aluno alunoSalvo = alunoRepository.getOne(id);
        if (alunoSalvo == null) {
            throw new EmptyResultDataAccessException("Aluno não encontrado", 1);
        }
        return alunoSalvo;
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        Aluno alunoSalvo = buscarAlunoPeloCodigo(id);
        BeanUtils.copyProperties(aluno, alunoSalvo, "id");
        return alunoRepository.save(alunoSalvo);
    }
    
}