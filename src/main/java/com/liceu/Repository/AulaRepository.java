package com.liceu.Repository;

import java.util.Date;
import java.util.List;

import com.liceu.Model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    List<Aula> findByDiaBetween(Date dataInicial, Date dataFinal);
}