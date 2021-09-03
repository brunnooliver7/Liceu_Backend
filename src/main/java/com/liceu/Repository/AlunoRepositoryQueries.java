package com.liceu.Repository;

import java.util.List;

import com.liceu.Model.Aluno;

public interface AlunoRepositoryQueries {
    List<Aluno> findNomeContemJpqlEstatico(String nome);
    List<Aluno> findNomeContemJpqlDinamico(String nome);
    List<Aluno> findNomeContemCriteriaEstatico(String nome);
    List<Aluno> findNomeContemCriteriaDinamico(String nome);
}
