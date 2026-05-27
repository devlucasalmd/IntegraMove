package com.br.integramove.application.pagamento.inputs;

import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.pagamento.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListarPagamentosInput(
        BigDecimal valor,
        LocalDate dataPagamento,
        LocalDate dataVencimento,
        FormaPagamento formaPagamento,
        StatusPagamento status
) {}
