package br.com.daniels.desafio5.repositories.params;

import br.com.daniels.desafio5.Models.ModelestoqueMovimento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepositoryCustom {
    List<ModelestoqueMovimento> listarHistorico(Integer id);


}
