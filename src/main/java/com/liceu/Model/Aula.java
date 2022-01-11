package com.liceu.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "aula")
@Data @NoArgsConstructor @AllArgsConstructor
public class Aula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dia;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;
  
    @ManyToMany
    @JoinTable(
        name="aula_aluno",
        joinColumns=@JoinColumn(name="aula_id"), 
        inverseJoinColumns=@JoinColumn(name="aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();

    @Embedded
    private Sala sala;
    
}