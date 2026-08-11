package com.br.integramove.application.treino.exercicio.outputs;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;

public record BuscarExercicioOutput(
        String id,
        String nome,
        GrupoMuscular grupoMuscular,
        String descricao,
        Intensidade intensidade,
        Boolean ativo
) {
}
