package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AvaliacaoResponseDTO(
        String id,
        LocalDate dataAvaliacao,
        BigDecimal peso,
        BigDecimal altura,
        BigDecimal imc,
        BigDecimal percentualGordura,
        BigDecimal circuferencia
) {
}
