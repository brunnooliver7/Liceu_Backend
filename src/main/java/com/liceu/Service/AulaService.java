package com.liceu.Service;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Aula;
import com.liceu.Repository.AulaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public Aula findOne(Long id) {
        return aulaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format("Não existe cadastro de aula com código %d", id)));
    }

    public void delete(Long id) {
        try {
            aulaRepository.deleteById(id);
        } catch (Exception e) {
			throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de aula com código %d", id));
        }
    }

}
