package com.br.integramove.application.treino.item;

import com.br.integramove.domain.treino.TreinoItemId;
import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.math.BigDecimal;

public record CriarTreinoItemOutput(
        String id,
        String exercicioId,
        Integer series,
        Integer repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {
}
