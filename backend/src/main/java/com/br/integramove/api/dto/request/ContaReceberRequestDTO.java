package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.CategoriaContaReceber;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaReceberRequestDTO(
        String descricao,
        CategoriaContaReceber categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        String alunoId,
        String planoId,
        String observacoes
) {
}
