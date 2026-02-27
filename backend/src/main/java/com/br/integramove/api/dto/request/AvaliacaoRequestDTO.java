package com.br.integramove.api.dto.request;

import java.time.LocalDate;

public record AvaliacaoRequestDTO(
        LocalDate dataAvaliacao,
        Double peso,
        Double altura,
        Double imc,
        Double percentualGordura,
        Double circuferencia
) {
}
