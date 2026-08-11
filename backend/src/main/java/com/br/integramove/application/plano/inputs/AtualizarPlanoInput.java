package com.br.integramove.application.plano.inputs;

import com.br.integramove.domain.enums.Periodicidade;

import java.math.BigDecimal;

public record AtualizarPlanoInput(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Periodicidade periodicidade,
        Integer duracaoDias,
        Boolean ativo
) {}