package com.br.integramove.application.plano.outputs;

import java.math.BigDecimal;

public record AtualizarPlanoOutput(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo) {
}
