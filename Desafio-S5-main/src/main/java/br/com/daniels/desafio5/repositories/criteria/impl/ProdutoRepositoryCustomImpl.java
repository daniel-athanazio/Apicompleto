package br.com.daniels.desafio5.repositories.criteria.impl;

import br.com.daniels.desafio5.Models.ModelProduto;
import br.com.daniels.desafio5.repositories.params.ProdutoFilterParam;
import br.com.daniels.desafio5.repositories.params.ProdutoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {
    private EntityManager entityManager;

    public ProdutoRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ModelProduto> getFiltro(ProdutoFilterParam params){

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ModelProduto> query = criteriaBuilder.createQuery(ModelProduto.class);

        Root<ModelProduto> produtoRoot = query.from(ModelProduto.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params.getDescricao() != null){
            predicates.add(criteriaBuilder.like(produtoRoot.get("descricao"), "%" + params.getDescricao() + "%"));
        }
        if (params.getValorProduto() != null){
            predicates.add(criteriaBuilder.equal(produtoRoot.get("valorProduto"), params.getValorProduto()));
        }
        if (params.getSaldoAtual() != null){
            predicates.add(criteriaBuilder.equal(produtoRoot.get("saldoAtual"), params.getSaldoAtual()));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.stream().toArray(Predicate[]::new));
        }

        TypedQuery<ModelProduto> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }

    public List<ModelProduto> listarPositivos(){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModelProduto> cq = cb.createQuery(ModelProduto.class);

        Root<ModelProduto> produtoRoot = cq.from(ModelProduto.class);

        cq.select(produtoRoot).where(cb.gt(produtoRoot.get("saldoAtual"),0));
        TypedQuery<ModelProduto> typedResult = this.entityManager.createQuery(cq);

        return typedResult.getResultList();
    }
}
