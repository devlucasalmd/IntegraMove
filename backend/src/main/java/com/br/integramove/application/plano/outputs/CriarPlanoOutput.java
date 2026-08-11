package com.br.integramove.application.plano.outputs;

import com.br.integramove.domain.enums.Periodicidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CriarPlanoOutput(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Periodicidade periodicidade,
        Integer duracaoDias,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}