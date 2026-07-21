package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaReceberResponseDTO(
        String id,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataRecebimento,
        String formaPagamento,
        String status,
        String alunoId,
        String planoId,
        String observacoes
) {
}
