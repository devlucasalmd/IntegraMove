package com.br.integramove.application.despesa.outputs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaOutput(
        String id,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String formaPagamento,
        String status,
        String fornecedor,
        String observacoes
) {
}
