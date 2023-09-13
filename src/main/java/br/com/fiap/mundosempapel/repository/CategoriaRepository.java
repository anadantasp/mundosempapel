package br.com.fiap.mundosempapel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.mundosempapel.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
