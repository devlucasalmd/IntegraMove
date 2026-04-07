package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoResponseDTO(
        String id,
        String formaPagamento,
        BigDecimal valor,
        LocalDate data,
        String status
) {
}
