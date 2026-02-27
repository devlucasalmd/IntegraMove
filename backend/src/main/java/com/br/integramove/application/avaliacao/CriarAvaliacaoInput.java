package com.br.integramove.application.avaliacao;

import java.time.LocalDate;

public record CriarAvaliacaoInput(
        LocalDate dataAvaliacao,
        Double peso,
        Double altura,
        Double imc,
        Double percentualGordura,
        Double circuferencia
) {
}
