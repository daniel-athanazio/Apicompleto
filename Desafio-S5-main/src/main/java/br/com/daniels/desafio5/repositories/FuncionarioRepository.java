package br.com.daniels.desafio5.repositories;

import br.com.daniels.desafio5.Models.ModelFuncionarioResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<ModelFuncionarioResponsavel, Integer> {
}
