package br.com.fiap.mundosempapel.controllers;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.mundosempapel.model.Produto;

@RestController
public class ProdutoController {
    
    @GetMapping("/produtos")
    public Produto index(){
        return new Produto(1L, "caderno", "caderno anotaçoes", BigDecimal.valueOf(85.90), "imagem.png");
    }
}
