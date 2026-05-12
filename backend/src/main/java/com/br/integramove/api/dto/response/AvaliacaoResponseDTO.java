package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AvaliacaoResponseDTO(
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
