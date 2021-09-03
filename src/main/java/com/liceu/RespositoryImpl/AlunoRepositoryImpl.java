package com.liceu.RespositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.liceu.Model.Aluno;
import com.liceu.Repository.AlunoRepositoryQueries;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class AlunoRepositoryImpl implements AlunoRepositoryQueries {

    @PersistenceContext
	private EntityManager manager;

    @Override
    public List<Aluno> findNomeContemJpqlEstatico(String nome) {
        
        var jpql = "from Aluno where nome like :nome";
        
		return manager
            .createQuery(jpql, Aluno.class)
			.setParameter("nome", "%" + nome + "%")
			.getResultList();
        
    }
        
    @Override
    public List<Aluno> findNomeContemJpqlDinamico(String nome) {

        var jpql = new StringBuilder();
        jpql.append("from Aluno where 0 = 0 ");
        
        var parametros = new HashMap<String, Object>();
        
        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        TypedQuery<Aluno> query = manager.createQuery(jpql.toString(), Aluno.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

    @Override
    public List<Aluno> findNomeContemCriteriaEstatico(String nome) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
        Root<Aluno> root = criteria.from(Aluno.class);
        Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
        criteria.where(nomePredicate);
        TypedQuery<Aluno> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Aluno> findNomeContemCriteriaDinamico(String nome) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
        Root<Aluno> root = criteria.from(Aluno.class);
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();
        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }
        criteria.where(predicates.toArray(new Predicate[0]));
        var query = manager.createQuery(criteria);
        return query.getResultList();
    }

}
