package com.br.integramove.application.plano;

import com.br.integramove.domain.plano.PlanoId;

import java.math.BigDecimal;

public record CriarPlanoOutput(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo
) {}
