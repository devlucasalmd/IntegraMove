package com.br.integramove.application.contrato.outputs;

import com.br.integramove.domain.enums.StatusContrato;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoOutput(
        String id,
        String alunoId,
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