package br.com.daniels.desafio5.controller;

import br.com.daniels.desafio5.repositories.params.ProdutoFilterParam;
import br.com.daniels.desafio5.Models.ModelProduto;
import br.com.daniels.desafio5.Services.produtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping(value = "/produtos")
@RestController
public class produtoController {
    @Autowired
    private produtoService service;

    @PostMapping(value = "/novo")
    public ResponseEntity novoProduto(@RequestBody ModelProduto produto){
       try {
           service.adicionarModelProduto(produto);
           return new ResponseEntity<>(produto, HttpStatus.CREATED);
       }catch (NoSuchElementException e){
           return new ResponseEntity<>("Produto não cadastrado",HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping
    public ResponseEntity listarTodos(){
       try {
           return new ResponseEntity(service.listarTodosOsProdutos(), HttpStatus.OK);
       }catch (NoSuchElementException e){
           return new ResponseEntity<>("Não a produtos cadastrados",HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity listarPorid(@PathVariable Integer id){
        try {
            return new ResponseEntity(service.buscarPorCodigo(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/positivos")
    public ResponseEntity produtosPositivos(){
        try {
            return new ResponseEntity(service.listarPositivos(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produtos não encontrados",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterarProduto(@PathVariable Integer id, @RequestBody ModelProduto produto){
        try {
            service.update(id, produto);
            return new ResponseEntity(produto, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produtos não encontrados",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity excluir(@PathVariable Integer id){
        try {
            service.delete(id);
            return new ResponseEntity("Id do produto apagado: " + id,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produtos não encontrados",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/descricao")
    public ResponseEntity listarPordescricao(@RequestParam(defaultValue = "")String buscar){
        try {
            return new ResponseEntity<>(service.ListarPorDescricao(buscar), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produtos não encontrados",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/filtro")
    public ResponseEntity filtrar(ProdutoFilterParam params){
        try {
            return new ResponseEntity(service.filtrar(params), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Produtos não encontrados",HttpStatus.NOT_FOUND);
        }
    }

}