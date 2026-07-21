package com.br.integramove.application.despesa.inputs;

import com.br.integramove.domain.enums.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarDespesaInput(
        String descricao,
        CategoriaDespesa categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        String fornecedor,
        String observacoes
) {
}
