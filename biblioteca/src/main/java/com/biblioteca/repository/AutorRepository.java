package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    List<Autor> findByNomeContainingIgnoreCase(String nome);
    
    Long countByNacionalidade(String nacionalidade);
    
}
