package com.br.integramove.application.plano;

import java.math.BigDecimal;

public record CriarPlanoInput(
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo
) {}
