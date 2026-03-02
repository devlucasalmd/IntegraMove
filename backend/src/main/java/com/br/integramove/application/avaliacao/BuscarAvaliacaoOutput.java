package com.br.integramove.application.avaliacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BuscarAvaliacaoOutput(
        String id,
        LocalDate dataAvaliacao,
        BigDecimal peso,
        BigDecimal altura,
        BigDecimal imc,
        BigDecimal percentualGordura,
        BigDecimal circuferencia
) {
}
