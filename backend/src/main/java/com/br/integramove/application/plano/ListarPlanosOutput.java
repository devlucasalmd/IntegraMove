package com.br.integramove.application.plano;

import java.math.BigDecimal;

public record ListarPlanosOutput(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo
) {
}
