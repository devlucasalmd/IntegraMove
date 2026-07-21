package com.br.integramove.application.contaReceber.inputs;

import com.br.integramove.domain.enums.CategoriaContaReceber;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarContaReceberInput(
        String descricao,
        CategoriaContaReceber categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        String alunoId,
        String planoId,
        String observacoes
) {
}
