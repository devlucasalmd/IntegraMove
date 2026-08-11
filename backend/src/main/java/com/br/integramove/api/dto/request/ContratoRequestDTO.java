package com.br.integramove.api.dto.request;

import java.time.LocalDate;

public record ContratoRequestDTO(
        String alunoId,
        String planoId,
        LocalDate dataInicio,
        Integer diaVencimento,
        Boolean permiteRenovacaoAutomatica
) {}