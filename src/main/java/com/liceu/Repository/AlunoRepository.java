package com.liceu.Repository;

import java.util.List;
import java.util.Optional;

import com.liceu.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByNome(String nome);
    Optional<Aluno> findByNome(String nome);
}