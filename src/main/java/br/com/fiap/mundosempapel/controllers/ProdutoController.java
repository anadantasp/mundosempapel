
package br.com.fiap.mundosempapel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mundosempapel.model.Produto;

@RestController
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Produto> produtos = new ArrayList<>();
    
    @GetMapping("/produtos")
    public List<Produto> index(){
        return produtos;
    }

    @PostMapping("/produtos")
    public ResponseEntity<List<Produto>> create(@RequestBody Produto produto){
       log.info("Cadastrando produtos - " + produto);
       produto.setId(produtos.size()+1L);
       produtos.add(produto);
       return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
    }

 
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("mostrar produto com id "  + id);
        var produtoEncontrado = produtos
        .stream()
        .filter((produto) -> produto.getId().equals(id))
        .findFirst();

        if(produtoEncontrado.isEmpty()){
        return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoEncontrado.get());
        
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        log.info("apagando produto com id " + id );
        var produtoEncontrado = produtos
        .stream()
        .filter((produto) -> produto.getId().equals(id))
        .findFirst();
        
        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        produtos.remove(produtoEncontrado.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto>update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando dados do produto com id " + id );
        var produtoEncontrado = produtos
        .stream()
        .filter((p) -> p.getId().equals(id))
        .findFirst();

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        produtos.remove(produtoEncontrado.get());
        produto.setId(id);
        produtos.add(produto);

        return ResponseEntity.ok(produto);
    }

}



