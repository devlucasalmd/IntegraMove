package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.enums.StatusVenda;
import com.br.integramove.domain.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record VendaResponseDTO(
        String id,
        String alunoId,
        TipoVenda tipo,
        String planoId,
        String descricao,
        BigDecimal valor,
        Periodicidade periodicidade,
        LocalDate dataVenda,
        StatusVenda status,
        String contratoId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}