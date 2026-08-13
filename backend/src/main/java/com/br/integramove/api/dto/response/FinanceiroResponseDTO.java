package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.FormaPagamento;
import com.br.integramove.domain.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FinanceiroResponseDTO(
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