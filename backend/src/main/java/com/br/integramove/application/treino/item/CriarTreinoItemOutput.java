package com.br.integramove.application.treino.item;

import java.math.BigDecimal;

public record CriarTreinoItemOutput(
        String id,
        String treinoId,
        String exercicioId,
        Integer series,
        String repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {
}
