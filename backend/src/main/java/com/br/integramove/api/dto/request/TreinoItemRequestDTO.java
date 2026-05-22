package com.br.integramove.api.dto.request;

import java.math.BigDecimal;

public record TreinoItemRequestDTO(
        String treinoId,
        String exercicioId,
        Integer series,
        String repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem,
        String observacao
) {}
