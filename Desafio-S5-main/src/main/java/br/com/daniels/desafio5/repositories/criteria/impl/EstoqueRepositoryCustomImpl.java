package br.com.daniels.desafio5.repositories.criteria.impl;

import br.com.daniels.desafio5.Models.ModelestoqueMovimento;
import br.com.daniels.desafio5.repositories.params.EstoqueRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EstoqueRepositoryCustomImpl implements EstoqueRepositoryCustom {

    private EntityManager entityManager;
    public EstoqueRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ModelestoqueMovimento> listarHistorico(Integer id){

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ModelestoqueMovimento> cq = cb.createQuery(ModelestoqueMovimento.class);

        Root<ModelestoqueMovimento> estoqueRoot = cq.from(ModelestoqueMovimento.class);

        cq.select(estoqueRoot).where(cb.equal(estoqueRoot.get("produto"), id));
        TypedQuery<ModelestoqueMovimento> typedResult = this.entityManager.createQuery(cq);
        return typedResult.getResultList();
    }
}