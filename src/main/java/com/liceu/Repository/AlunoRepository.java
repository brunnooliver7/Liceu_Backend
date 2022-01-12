package com.liceu.Repository;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends CustomJpaRepository<Aluno, Long>, AlunoRepositoryQueries, JpaSpecificationExecutor<Aluno> {
    
    @Query("from Aluno a join fetch a.aulas")
    List<Aluno> findAll();
    
    List<Aluno> findAllByNome(String nome);
    List<Aluno> findAllByNomeContaining(String nome);
    Optional<Aluno> findByNome(String nome);
    Optional<Aluno> findFirstByNome(String nome);
    Optional<Aluno> findFirstByNomeContaining(String nome);
    Optional<Aluno> existsByNome(String nome);
    List<Aluno> findTop2ByNomeContaining(String nome);
    int countByNome(String nome);
    int countByNomeContaining(String nome);
    Aluno consultarPorNome(String nome);
}