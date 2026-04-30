package com.biblioteca.dto;

import java.math.BigDecimal;

public class LivroDTO {

    private String titulo;
    private BigDecimal preco;

    public LivroDTO(String titulo, BigDecimal preco) {
        this.titulo = titulo;
        this.preco = preco;
    }
}