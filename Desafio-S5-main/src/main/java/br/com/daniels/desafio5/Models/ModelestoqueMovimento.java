package br.com.daniels.desafio5.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity(name = "tb_estoque_movimento")
public class ModelestoqueMovimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ModelProduto produto;

    @ManyToOne
    private ModelFuncionarioResponsavel funcionarioResponsavel;

    private Timestamp dataHora;

    @Column(length = 1)
    private String tipoMovimento;

    private Integer quantidade;
}
