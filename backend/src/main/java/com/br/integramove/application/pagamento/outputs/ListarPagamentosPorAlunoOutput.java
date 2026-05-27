package com.br.integramove.application.pagamento.outputs;

import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.pagamento.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListarPagamentosPorAlunoOutput(
        String id,
        String alunoId,
        String planoId,
        BigDecimal valor,
        LocalDate dataPagamento,
        LocalDate dataVencimento,
        FormaPagamento formaPagamento,
        StatusPagamento status
) {
}
