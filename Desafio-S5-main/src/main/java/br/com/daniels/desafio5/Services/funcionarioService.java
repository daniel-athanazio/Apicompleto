package br.com.daniels.desafio5.Services;

import br.com.daniels.desafio5.repositories.FuncionarioRepository;
import br.com.daniels.desafio5.Models.ModelFuncionarioResponsavel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class funcionarioService {

    private List<ModelFuncionarioResponsavel> funcionario = new ArrayList<>();
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public void adicionarModelFuncionario(ModelFuncionarioResponsavel funcionarioQueSeraSalvo){
        funcionarioRepository.save(funcionarioQueSeraSalvo);
    }
    public List<ModelFuncionarioResponsavel> listarTodosOsFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public ModelFuncionarioResponsavel buscarPorId(Integer id){
        Optional<ModelFuncionarioResponsavel> ModelFuncionarioOPT = funcionarioRepository.findById(id);
        if (ModelFuncionarioOPT.isEmpty()){
            return null;
        }
        return ModelFuncionarioOPT.get();
    }

    public void update(Integer id, ModelFuncionarioResponsavel ModelFuncionario) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.save(ModelFuncionario);
        }
    }

    public void delete(Integer id) {
        if (funcionarioRepository.existsById(id)){
            funcionarioRepository.deleteById(id);
        }
    }

}
