package com.br.integramove.api.dto.response;

import java.math.BigDecimal;

public record PlanoResponseDTO(
        String id,
        String nome,
        BigDecimal valor,
        String descricao,
        Boolean ativo
) {
}
