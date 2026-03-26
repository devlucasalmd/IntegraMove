package com.br.integramove.api.dto.request;

import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import com.br.integramove.domain.treino.exercicio.Intensidade;

public record ExercicioRequestDTO (
        String nome,
        GrupoMuscular grupoMuscular,
        String descricao,
        Intensidade intensidade,
        Boolean ativo
) {}
