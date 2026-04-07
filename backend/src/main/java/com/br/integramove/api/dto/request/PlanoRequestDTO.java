package com.br.integramove.api.dto.request;

import java.math.BigDecimal;

public record PlanoRequestDTO(
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo
) {}
