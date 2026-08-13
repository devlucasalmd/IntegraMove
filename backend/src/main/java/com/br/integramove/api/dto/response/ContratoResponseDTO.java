package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.StatusContrato;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoResponseDTO(
        String id,
        String alunoId,
        String vendaId,
        String planoId,
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer diaVencimento,
        Boolean permiteRenovacaoAutomatica,
        StatusContrato status,
        String documentoUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}