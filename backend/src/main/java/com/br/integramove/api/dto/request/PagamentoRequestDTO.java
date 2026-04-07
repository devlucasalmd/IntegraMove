package com.br.integramove.api.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoRequestDTO(
        String formaPagamento,
        BigDecimal valor,
        LocalDate data,
        String status
) {}
