package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.Periodicidade;

import java.math.BigDecimal;

public record PlanoRequestDTO(
        String nome,
        BigDecimal valor,
        String descricao,
        Periodicidade periodicidade,
        Integer duracaoDias,
        Boolean ativo
) {}