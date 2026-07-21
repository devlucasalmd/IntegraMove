package com.br.integramove.api.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoRequestDTO(
        String planoId,
        BigDecimal valor,
        LocalDate dataVencimento,
        String observacoes
) {}
