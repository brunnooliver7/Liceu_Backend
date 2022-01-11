package com.liceu.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Sala {

  @Column(name = "sala_bloco")
  private String bloco;
  
  @Column(name = "sala_andar")
  private Integer andar;
  
  @Column(name = "sala_numero")
  private Integer numero;

}
