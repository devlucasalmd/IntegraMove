package com.br.integramove.application.treino.treino;

import com.br.integramove.application.treino.item.TreinoItemOutput;
import com.br.integramove.domain.treino.exercicio.GrupoMuscular;

import java.util.List;

public record ListarTreinosOutput(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        GrupoMuscular grupoMuscular
) {}
