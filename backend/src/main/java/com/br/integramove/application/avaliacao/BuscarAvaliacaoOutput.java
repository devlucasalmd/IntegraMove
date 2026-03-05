package com.br.integramove.application.avaliacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BuscarAvaliacaoOutput(
        String id,
        LocalDate dataAvaliacao,
        BigDecimal remadaBracoD,
        BigDecimal remadaBracoE,
        BigDecimal elevacaoLatD,
        BigDecimal elevacaoLatE,
        BigDecimal extensaoJoelhoD,
        BigDecimal extensaoJoelhoE,
        BigDecimal flexaoJoelhoD,
        BigDecimal flexaoJoelhoE,
        BigDecimal extensaoQuadrilD,
        BigDecimal extensaoQuadrilE
) {
}
