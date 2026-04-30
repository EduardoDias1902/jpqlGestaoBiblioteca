package com.biblioteca.mapper;

import com.biblioteca.model.Livro;
import com.biblioteca.dto.LivroDTO;

public class LivroMapper {

    public static LivroDTO toDTO(Livro livro) {
        return new LivroDTO(livro.getTitulo(), livro.getPreco());
    }
}