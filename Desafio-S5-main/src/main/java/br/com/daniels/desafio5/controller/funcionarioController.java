package br.com.daniels.desafio5.controller;

import br.com.daniels.desafio5.Models.ModelFuncionarioResponsavel;
import br.com.daniels.desafio5.Services.funcionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping(value = "/funcionarios")
@RestController
public class funcionarioController {

    @Autowired
    private funcionarioService service;

    @PostMapping(value = "/novo")
    public ResponseEntity novoFuncionario(@RequestBody ModelFuncionarioResponsavel funcionario){
        try {
            service.adicionarModelFuncionario(funcionario);
            return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Funcionario não cadastrado",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity listarTodos(){
        try {
            return new ResponseEntity(service.listarTodosOsFuncionarios(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Não a funcionarios cadastrados", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity listarPorid(@PathVariable Integer id){
        try {
            return new ResponseEntity(service.buscarPorId(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterarFuncionario(@PathVariable Integer id, @RequestBody ModelFuncionarioResponsavel funcionario){
        try {
            service.update(id, funcionario);
            return new ResponseEntity(funcionario, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Funcionario não encontrado",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity excluir(@PathVariable Integer id){
        try {
            service.delete(id);
            return new ResponseEntity("Id do funcionario demitido: " + id,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Funcionario não encontrado",HttpStatus.NOT_FOUND);
        }
    }
}
