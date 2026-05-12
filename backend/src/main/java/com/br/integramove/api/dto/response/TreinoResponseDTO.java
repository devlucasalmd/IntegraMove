package com.br.integramove.api.dto.response;

import com.br.integramove.application.treino.item.TreinoItemOutput;

import java.util.List;

public record TreinoResponseDTO(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes
) {
}
