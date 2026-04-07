package com.br.integramove.application.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListarPagamentosOutput(
        String id,
        String formaPagamento,
        BigDecimal valor,
        LocalDate data,
        String status
) {}
