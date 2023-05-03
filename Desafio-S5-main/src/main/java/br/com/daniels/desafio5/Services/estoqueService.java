package br.com.daniels.desafio5.Services;

import br.com.daniels.desafio5.repositories.EstoqueRepository;
import br.com.daniels.desafio5.Models.ModelProduto;
import br.com.daniels.desafio5.Models.ModelestoqueMovimento;
import br.com.daniels.desafio5.repositories.params.EstoqueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class estoqueService {
    private List<ModelestoqueMovimento> estoques = new ArrayList<>();

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueRepositoryCustom estoqueRepositoryCustom;

    @Autowired
    private produtoService produtoService;

    public void adicionarestoques(ModelestoqueMovimento estoqueSerSalvo){
        ModelProduto produto = produtoService.buscarPorCodigo(estoqueSerSalvo.getProduto().getProId());
        if (estoqueSerSalvo.getTipoMovimento().equals("E")){
            produto.setSaldoAtual(produto.getSaldoAtual() + estoqueSerSalvo.getQuantidade());
            produtoService.update(produto.getProId(), produto);
        }else {
            produto.setSaldoAtual(produto.getSaldoAtual() - estoqueSerSalvo.getQuantidade());
            produtoService.update(produto.getProId(), produto);
        }

        estoqueRepository.save(estoqueSerSalvo);
    }

    public ModelestoqueMovimento buscarPorId(Integer id){
        Optional<ModelestoqueMovimento> modelestoqueOPT = estoqueRepository.findById(id);
        if (modelestoqueOPT.isEmpty()){
            return null;
        }
        return modelestoqueOPT.get();
    }

    public void update(Integer id, ModelestoqueMovimento estoque) {
        ModelestoqueMovimento verifica = buscarPorId(id);
        verifica.getFuncionarioResponsavel();
        verifica.getProduto();

        estoque.setProduto(verifica.getProduto());
        estoque.setFuncionarioResponsavel(verifica.getFuncionarioResponsavel());

        if (estoqueRepository.existsById(id)){
            estoqueRepository.save(estoque);
        }
    }

    public void delete(Integer id) {
        if (estoqueRepository.existsById(id)){
            estoqueRepository.deleteById(id);
        }
    }


    public List<ModelestoqueMovimento> listarHistorico(Integer id){
        return estoqueRepositoryCustom.listarHistorico(id);
    }

    public List<ModelestoqueMovimento> listarTodosOsEstoques() {
        return estoqueRepository.findAll();
    }
}
