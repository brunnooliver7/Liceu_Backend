package com.liceu.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "aluno")
@JsonRootName("aluno") @XmlRootElement(name = "aluno")
@Data @NoArgsConstructor @AllArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    private int matricula;

    private int classificacao;

    private BigDecimal mensalidade;

    @ManyToMany
    @JoinTable(
        name = "aula_aluno",
        joinColumns = @JoinColumn(name = "aluno_id"), 
        inverseJoinColumns = @JoinColumn(name = "aula_id")
    )
    private List<Aula> aulas = new ArrayList<>();
}