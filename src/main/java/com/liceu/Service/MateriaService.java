package com.liceu.Service;

import com.liceu.Exception.EntidadeNaoEncontradaException;
import com.liceu.Model.Materia;
import com.liceu.Repository.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {
  
  @Autowired
  private MateriaRepository materiaRepository;

  public Materia findOne(Long id) {
      return materiaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
          String.format("Não existe cadastro de matéria com código %d", id)));
  }

  public void delete(Long id) {
      try {
          materiaRepository.deleteById(id);
      } catch (Exception e) {
    throw new EntidadeNaoEncontradaException(
              String.format("Não existe um cadastro de matéria com código %d", id));
      }
  }

}
