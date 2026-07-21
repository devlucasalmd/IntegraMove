package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.StatusFinanceiro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinanceiroAlunoResponseDTO(
        String alunoId,
        String nomeAluno,
        String planoId,
        String nomePlano,
        BigDecimal valorPlano,
        StatusFinanceiro statusFinanceiro,
        BigDecimal totalPago,
        BigDecimal totalEmAberto,
        BigDecimal totalVencido,
        LocalDate ultimoPagamento,
        LocalDate proximoVencimento
) {
}
