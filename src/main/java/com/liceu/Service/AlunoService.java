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

    public Aluno buscarAlunoPeloCodigo(Long codigo) {
        Aluno alunoSalvo = alunoRepository.getOne(codigo);
        if (alunoSalvo == null) {
            throw new EmptyResultDataAccessException("Aluno não encontrado", 1);
        }
        return alunoSalvo;
    }

    public Aluno atualizar(Long codigo, Aluno aluno) {
        Aluno alunoSalvo = buscarAlunoPeloCodigo(codigo);
        BeanUtils.copyProperties(aluno, alunoSalvo, "codigo");
        return alunoRepository.save(alunoSalvo);
    }
    
}