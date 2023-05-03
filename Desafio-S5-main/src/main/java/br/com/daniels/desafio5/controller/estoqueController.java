package br.com.daniels.desafio5.controller;

import br.com.daniels.desafio5.Models.ModelFuncionarioResponsavel;
import br.com.daniels.desafio5.Models.ModelProduto;
import br.com.daniels.desafio5.Models.ModelestoqueMovimento;
import br.com.daniels.desafio5.Services.estoqueService;
import br.com.daniels.desafio5.Services.produtoService;
import br.com.daniels.desafio5.Services.funcionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping(value = "/estoque")
@RestController
public class estoqueController {

    @Autowired
    private estoqueService service;

    @Autowired
    private funcionarioService funcionarioService;

    @Autowired
    private produtoService produtoService;

    @PostMapping(value = "/novo")
    public ResponseEntity novoEstoque(@RequestBody ModelestoqueMovimento estoque,
                                      @RequestParam Integer proId, @RequestParam Integer funId){
        ModelProduto produto = produtoService.buscarPorCodigo(proId);
        estoque.setProduto(produto);
        service.adicionarestoques(estoque);
        ModelFuncionarioResponsavel funcionario = funcionarioService.buscarPorId(funId);
        estoque.setFuncionarioResponsavel(funcionario);

        try {
            service.adicionarestoques(estoque);
            return new ResponseEntity<>(estoque, HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Estoque não cadastrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity listarPorid(@PathVariable Integer id){
        try {
            return new ResponseEntity(service.buscarPorId(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Estoque não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity listarTodos(){
        try {
            return new ResponseEntity(service.listarTodosOsEstoques(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Estoque não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/historico/{id}")
    public ResponseEntity listarHistorico(@PathVariable Integer id){
       try {
           return new ResponseEntity(service.listarHistorico(id), HttpStatus.OK);
       }catch (NoSuchElementException e){
            return new ResponseEntity<>("Historico do produto não encontrado", HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterarEstoque(@PathVariable Integer id,
                                         @RequestBody ModelestoqueMovimento estoque){
        try {
            service.update(id, estoque);
            return new ResponseEntity("Estoque alterado",HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Estoque não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity excluir(@PathVariable Integer id) {

        try {
            service.delete(id);
            return new ResponseEntity("Id do estoque excluido: " + id, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Estoque não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
