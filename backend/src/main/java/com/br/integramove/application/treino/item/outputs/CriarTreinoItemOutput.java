package com.br.integramove.application.treino.item.outputs;

import java.math.BigDecimal;

public record CriarTreinoItemOutput(
        String id,
        String exercicioId,
        String nomeExercicio,
        Integer series,
        String repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {
}
