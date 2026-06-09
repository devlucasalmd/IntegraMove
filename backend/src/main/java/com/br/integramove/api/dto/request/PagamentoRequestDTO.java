package com.br.integramove.api.dto.request;

import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.pagamento.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoRequestDTO(
        String planoId,
        BigDecimal valor,
        LocalDate dataVencimento,
        String observacoes
) {}
