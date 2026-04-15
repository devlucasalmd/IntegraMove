package com.br.integramove.application.treino.treino;

import com.br.integramove.application.treino.item.TreinoItemOutput;

import java.util.List;

public record BuscarTreinoOutput(
        String id,
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        List<TreinoItemOutput> exercicios
) {
}
