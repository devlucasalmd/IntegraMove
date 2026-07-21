package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarDespesaRequestDTO(
        String descricao,
        CategoriaDespesa categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        String fornecedor,
        String observacoes
) {
}
