package br.com.daniels.desafio5.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_produto")
public class ModelProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;

    private String descricao;
    private BigDecimal valorVenda;
    private Integer saldoAtual;
}
