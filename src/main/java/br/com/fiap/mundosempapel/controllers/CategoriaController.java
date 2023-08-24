package br.com.fiap.mundosempapel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mundosempapel.model.Categoria;

@RestController
public class CategoriaController {

    @GetMapping("/categorias")
    public Categoria index(){
        return new Categoria(1L, "Cadernos");
    }
    
}
