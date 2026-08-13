package com.br.integramove.application.financeiro.outputs;

import com.br.integramove.domain.enums.FormaPagamento;
import com.br.integramove.domain.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * status aqui é sempre o statusCalculado() do domínio (ATRASADO derivado na
 * leitura para PENDENTE vencido), nunca o valor bruto persistido.
 */
public record FinanceiroOutput(
        String id,
        String alunoId,
        String vendaId,
        String contratoId,
        BigDecimal valor,
        Integer numeroParcela,
        Integer totalParcelas,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        StatusPagamento status,
        FormaPagamento formaPagamento,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}