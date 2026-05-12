package com.br.integramove.application.treino.item;

import java.math.BigDecimal;

public record BuscarTreinoItemOutput(
        String id,
        String exercicioId,
        Integer series,
        String repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {
}
