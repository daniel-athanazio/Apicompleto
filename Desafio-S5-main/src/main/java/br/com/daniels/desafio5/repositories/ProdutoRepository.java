package br.com.daniels.desafio5.repositories;

import br.com.daniels.desafio5.Models.ModelProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<ModelProduto, String> {
    public List<ModelProduto> findByDescricaoContainingIgnoreCase(String descricao);

    public List<ModelProduto> findBysaldoAtual(Integer saldoAtual);
}
