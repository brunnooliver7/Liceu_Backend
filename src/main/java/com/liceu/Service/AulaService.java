package com.liceu.Service;

import com.liceu.Model.Aula;
import com.liceu.Repository.AulaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AulaService {
    
    @Autowired
    private AulaRepository aulaRepository;

    public Aula buscarAulaPeloCodigo(Long id) {
        Aula aulaSalvo = aulaRepository.getOne(id);
        if (aulaSalvo == null) {
            throw new EmptyResultDataAccessException("Aula não encontrada", 1);
        }
        return aulaSalvo;
    }

    public Aula atualizar(Long id, Aula aula) {
        Aula aulaSalvo = buscarAulaPeloCodigo(id);
        BeanUtils.copyProperties(aula, aulaSalvo, "id");
        return aulaRepository.save(aulaSalvo);
    }

}