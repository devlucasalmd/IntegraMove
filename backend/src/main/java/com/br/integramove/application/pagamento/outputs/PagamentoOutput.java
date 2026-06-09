package com.br.integramove.application.pagamento.outputs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoOutput(
        String id,
        String alunoId,
        String planoId,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String formaPagamento,
        String status,
        String observacoes
) {
}
