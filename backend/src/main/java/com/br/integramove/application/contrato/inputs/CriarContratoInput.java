package com.br.integramove.application.contrato.inputs;

import java.time.LocalDate;

public record CriarContratoInput(
        String alunoId,
        String vendaId,
        String planoId,
        LocalDate dataInicio,
        Integer diaVencimento,
        Boolean permiteRenovacaoAutomatica
) {}