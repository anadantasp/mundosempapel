package br.com.fiap.mundosempapel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.mundosempapel.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
