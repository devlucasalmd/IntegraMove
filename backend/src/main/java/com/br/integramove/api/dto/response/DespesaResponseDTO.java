package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponseDTO(
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
