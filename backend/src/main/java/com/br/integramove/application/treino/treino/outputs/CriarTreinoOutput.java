package com.br.integramove.application.treino.treino.outputs;

import com.br.integramove.domain.enums.GrupoMuscular;

public record CriarTreinoOutput(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        GrupoMuscular grupoMuscular
){}
