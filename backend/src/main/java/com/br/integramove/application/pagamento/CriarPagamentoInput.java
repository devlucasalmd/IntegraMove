package com.br.integramove.application.pagamento;

import com.br.integramove.domain.pagamento.PagamentoId;
import com.br.integramove.infrastructure.persistence.pagamento.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CriarPagamentoInput(
        String formaPagamento,
        BigDecimal valor,
        LocalDate data,
        String status
) {}
