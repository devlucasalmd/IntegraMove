package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.Periodicidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlanoResponseDTO(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Periodicidade periodicidade,
        Integer duracaoDias,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}