package com.br.integramove.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoResponseDTO(
        String id,
        String alunoId,
        String planoId,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String formaPagamento,
        String status,
        String observacoes
) {}
