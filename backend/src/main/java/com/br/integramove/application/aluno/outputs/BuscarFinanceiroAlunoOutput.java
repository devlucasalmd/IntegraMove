package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.pagamento.StatusFinanceiro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BuscarFinanceiroAlunoOutput(
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
) {}
