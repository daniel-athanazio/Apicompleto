package br.com.daniels.desafio5.Models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "tb_funcionario_responsavel")
public class ModelFuncionarioResponsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer funcionarioId;

    @Column(length = 255)
    private String nome;

    @Column(length = 50)
    private String cpf;

    @Column(length = 20)
    private String cargo;
}
