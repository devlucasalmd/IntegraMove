package com.br.integramove.api.dto.response;

import java.time.LocalDate;

public record AvaliacaoResponseDTO(
        String id,
        LocalDate dataAvaliacao,
        Double peso,
        Double altura,
        Double imc,
        Double percentualGordura,
        Double circuferencia
) {
}
