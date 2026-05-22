package com.br.integramove.api.dto.response;

import java.math.BigDecimal;

public record TreinoItemResponseDTO(
        String treinoItemId,
        String exercicioId,
        String nome,
        Integer series,
        String repeticoes,
        BigDecimal carga,
        Integer descanso,
        Integer ordem
) {}
