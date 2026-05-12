package com.br.integramove.api.dto.request;

import com.br.integramove.application.treino.item.TreinoItemOutput;

import java.util.ArrayList;
import java.util.List;

public record TreinoRequestDTO(
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        List<TreinoItemRequestDTO> exercicios
) {
    public TreinoRequestDTO {
        exercicios = exercicios == null
                ? new ArrayList<>()
                : exercicios;
    }
}



