package com.liceu.Service;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findOne(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format("Não existe cadastro de aluno com código %d", id)));
    }

    public void delete(Long id) {
        try {
            alunoRepository.deleteById(id);
        } catch (Exception e) {
			throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de aluno com código %d", id));
        }
    }    

}
