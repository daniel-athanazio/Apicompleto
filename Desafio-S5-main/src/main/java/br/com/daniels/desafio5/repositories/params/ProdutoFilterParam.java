package br.com.daniels.desafio5.repositories.params;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
public class ProdutoFilterParam {
    private String descricao;
    private BigDecimal valorProduto;
    private Integer saldoAtual;
}
