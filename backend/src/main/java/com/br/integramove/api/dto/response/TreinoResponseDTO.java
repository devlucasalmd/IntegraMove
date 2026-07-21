package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.GrupoMuscular;

public record TreinoResponseDTO(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        GrupoMuscular grupoMuscular
) {
}
