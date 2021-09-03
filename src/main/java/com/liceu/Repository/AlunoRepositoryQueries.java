package com.liceu.Repository;

import java.util.List;

import com.liceu.Model.Aluno;

public interface AlunoRepositoryQueries {
    List<Aluno> findNomeContem(String nome);
}
