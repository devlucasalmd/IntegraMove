package com.br.integramove.api.dto.response;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import com.br.integramove.domain.treino.exercicio.Intensidade;

public record ExercicioResponseDTO(
        String id,
        String nome,
        GrupoMuscular grupoMuscular,
        String descricao,
        Intensidade intensidade,
        Boolean ativo
) {
}
