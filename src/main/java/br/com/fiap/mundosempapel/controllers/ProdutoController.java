
package br.com.fiap.mundosempapel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.fiap.mundosempapel.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    
    @GetMapping("/produtos")
    public List<Produto> index(){
        log.info("Buscando todas as categorias");
        return produtoRepository.findAll();
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto){
       log.info("Cadastrando produtos - " + produto);
       produtoRepository.save(produto);
       return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

 
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("mostrar produto com id "  + id);
        return ResponseEntity.ok(getProdutoById(id));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        log.info("apagando produto com id " + id );
        
        produtoRepository.delete(getProdutoById(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto>update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando dados do produto com id " + id );

        getProdutoById(id);
        produto.setId(id);
        produtoRepository.save(produto);

        return ResponseEntity.ok(produto);
    }

    private Produto getProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> { 
             return new RuntimeException();
         });
     }

}



