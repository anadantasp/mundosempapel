package br.com.fiap.mundosempapel.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.mundosempapel.model.Categoria;
import br.com.fiap.mundosempapel.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> categorias = new ArrayList<>();

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List<Categoria> index(){
        return categoriaRepository.findAll();
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){
        log.info("cadastrando categoria " + categoria);
        categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping ("/categorias/{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscando a categoria de id " + id);
        return ResponseEntity.ok(getCategoriaById(id));

    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("deletando a categoria de id " + id);

        categoriaRepository.delete(getCategoriaById(id));

        return ResponseEntity.noContent().build();
        
    }

    @PutMapping("categorias/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando a categoria de id " + id);

        getCategoriaById(id);
        categoria.setId(id);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);

    }

    private Categoria getCategoriaById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> { 
             return new RuntimeException();
         });
     }
    
}
