package com.br.integramove.application.avaliacao.outputs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListarAvaliacaoOutput(
        String id,
        String alunoId,
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
