package com.br.integramove.api.dto.request;

import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.pagamento.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoRequestDTO(
        String alunoId,
        LocalDate dataPagamento,
        LocalDate dataVencimento,
        FormaPagamento formaPagamento,
        StatusPagamento status
) {}
