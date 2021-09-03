package com.liceu.RespositoryImpl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepositoryQueries;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class AlunoRepositoryImpl implements AlunoRepositoryQueries {

    @PersistenceContext
	private EntityManager manager;

    @Override
    public List<Aluno> findNomeContem(String nome) {
		
        // var jpql = "from Aluno where nome like :nome";

		// return manager
        //     .createQuery(jpql, Aluno.class)
		// 	.setParameter("nome", "%" + nome + "%")
		// 	.getResultList();
            
        var jpql = new StringBuilder();
        jpql.append("from Aluno where 0 = 0 ");
        
        var parametros = new HashMap<String, Object>();
        
        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        TypedQuery<Aluno> query = manager
        .createQuery(jpql.toString(), Aluno.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }
}
