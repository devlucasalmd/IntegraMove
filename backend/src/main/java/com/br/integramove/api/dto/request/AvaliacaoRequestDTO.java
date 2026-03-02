package com.br.integramove.api.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AvaliacaoRequestDTO(
        LocalDate dataAvaliacao,
        BigDecimal peso,
        BigDecimal altura,
        BigDecimal imc,
        BigDecimal percentualGordura,
        BigDecimal circuferencia
) {
}
