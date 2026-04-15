package com.br.integramove.application.treino.treino;

import com.br.integramove.application.treino.item.CriarTreinoItemInput;

import java.util.List;

public record CriarTreinoInput(
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        List<CriarTreinoItemInput> exercicios
) {
}
