package com.br.integramove.application.treino.treino.outputs;

import com.br.integramove.application.treino.item.outputs.TreinoItemOutput;
import com.br.integramove.domain.enums.GrupoMuscular;

import java.util.List;

public record BuscarTreinoOutput(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        GrupoMuscular grupoMuscular,
        List<TreinoItemOutput> exercicios
) {
}
