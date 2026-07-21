package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;

public record ExercicioRequestDTO (
        String nome,
        GrupoMuscular grupoMuscular,
        String descricao,
        Intensidade intensidade,
        Boolean ativo
) {}
