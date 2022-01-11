package com.liceu.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "professor")
@JsonRootName("professor") @XmlRootElement(name = "professor")
@Data @NoArgsConstructor @AllArgsConstructor
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("nome")
    private String nome;
    
    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Materia> materias = new ArrayList<>();

    @Embedded
    private Sala sala;
}