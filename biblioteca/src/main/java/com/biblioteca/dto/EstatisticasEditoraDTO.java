package com.biblioteca.dto;

import lombok.Getter;

@Getter
public class EstatisticasEditoraDTO {

    private String editora;
    private Long quantidade;

    public EstatisticasEditoraDTO(String editora, Long quantidade) {
        this.editora = editora;
        this.quantidade = quantidade;
    }

}