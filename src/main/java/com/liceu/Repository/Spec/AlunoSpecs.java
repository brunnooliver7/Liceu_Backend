package com.liceu.Repository.Spec;

import java.math.BigDecimal;

import com.liceu.Model.Aluno;

import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecs {
    
    public static Specification<Aluno> comBolsaIntegral() {
		return (root, query, builder) -> 
			builder.equal(root.get("mensalidade"), BigDecimal.ZERO);
    }
    
    public static Specification<Aluno> comNomeSemelhante(String nome) {
		return (root, query, builder) -> 
            builder.like(root.get("nome"), "%" + nome + "%");
    }

}
