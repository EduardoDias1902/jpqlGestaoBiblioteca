package com.biblioteca.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.dto.EstatisticasEditoraDTO;
import com.biblioteca.model.Livro;
import com.biblioteca.projections.LivroMinimoProjection;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByCategoriaAndPrecoLassThan(String categoria, BigDecimal preco);
    
    List<Livro> findByPrecoBetween(BigDecimal min,BigDecimal max);

    List<Livro> findByIsbnIsNull();

    List<Livro> findByEditoraNomeOrderByTituloAsc(String nome);

    @Query("SELECT l.titulo FROM Livro l WHERE l.categoria = :categoria")
    List<String> buscarTitulosPorCategoria(@Param("categoria") String categoria);

    @Query("""
        SELECT l 
        FROM Livro l 
        JOIN l.autores a 
        WHERE a.nome = :nome
            """)
    List<Livro> buscarPorAutores(@Param("nome")String nome);

    @Query("""
        SELECT l 
        FROM Livro 
        JOIN FETCH l.autores
            """)
    List<Livro> buscarComAutores();

    @Query("""
        SELECT AVG(l.preco)
         FROM Livro l 
         WHERE l.editora.nome = :nome
            """ )
    Double mediaPrecoPorEditora(@Param ("nome")String nome);

    @Query("""
        SELECT AVG(l.preco)
        FROM Livro l 
        WHERE l.preco > (SELECT AVG(l2.preco) FROM Livro l2)    
            """)
    List<Livro> acimaDaMedia();

    @Query(value =
            """
        SELECT *
        FROM livros
        WHERE YEAR (data_publicacao) = 2023
            """, nativeQuery = true)
    List<Livro> livros2023();

    @Query(value =
        """
        SELECT *
        FROM livros
        WHERE LOWER(categoria) = LOWER(:categoria)    
            """,
        nativeQuery = true
    )
    List<Livro> buscarCategoriaIgnoreCase(@Param("categoria")String categoria);

    List<LivroMinimoProjection> findByCategoria(String categoria);    

    @Query("""
            SELECT new com.biblioteca.dto.EstatisticaEditoraDTO(l.editora.nome, COUNT(1))
            FROM Livro l 
            GROUP BY l.editora.nome
            
            """)
            List<EstatisticasEditoraDTO> estatisticas();
}
