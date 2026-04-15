package com.br.integramove.application.treino.item;

import java.math.BigDecimal;

public record TreinoItemOutput(
        String exercicioId,
        String nomeExercicio,
        Integer series,
        Integer repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {
}
