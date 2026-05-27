package com.br.integramove.application.treino.treino;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;

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
