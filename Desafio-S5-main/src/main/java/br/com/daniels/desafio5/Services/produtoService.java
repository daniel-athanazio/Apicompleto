package br.com.daniels.desafio5.Services;

import br.com.daniels.desafio5.repositories.ProdutoRepository;
import br.com.daniels.desafio5.repositories.params.ProdutoFilterParam;
import br.com.daniels.desafio5.Models.ModelProduto;
import br.com.daniels.desafio5.repositories.criteria.impl.ProdutoRepositoryCustomImpl;
import br.com.daniels.desafio5.repositories.params.ProdutoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class produtoService {
    private List<ModelProduto> produtos = new ArrayList<>();
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoRepositoryCustom produtoRepositoryCustom;

    @Autowired
    private ProdutoRepositoryCustomImpl produtoRepositoryCustomImpl;
    public void adicionarModelProduto(ModelProduto produtoQueSeraSalvo){
        produtoRepository.save(produtoQueSeraSalvo);
    }
    public List<ModelProduto> listarTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    public ModelProduto buscarPorCodigo(Integer id){
        Optional<ModelProduto> ModelprodutoOPT = produtoRepository.findById(String.valueOf(id));
        if (ModelprodutoOPT.isEmpty()){
            return null;
        }
        return ModelprodutoOPT.get();
    }

    public void update(Integer id, ModelProduto Modelproduto) {
        if (produtoRepository.existsById(String.valueOf(id))){
            produtoRepository.save(Modelproduto);
        }
    }

    public void delete(Integer id) {
        if (produtoRepository.existsById(String.valueOf(id))){
            produtoRepository.deleteById(String.valueOf(id));
        }
    }

    public List<ModelProduto> ListarPorDescricao(String parametroBusca) {
        return produtoRepository.findByDescricaoContainingIgnoreCase(parametroBusca);
    }

    public List<ModelProduto> listarPositivos(){
        return produtoRepositoryCustom.listarPositivos();
    }

    public List<ModelProduto> filtrar(ProdutoFilterParam params) {
        return produtoRepositoryCustom.getFiltro(params);
    }
}