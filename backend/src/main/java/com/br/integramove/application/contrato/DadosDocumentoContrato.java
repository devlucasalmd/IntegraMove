package com.br.integramove.application.contrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDocumentoContrato(
        String contratoId,
        String alunoNome,
        String planoNome,
        BigDecimal planoValor,
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer diaVencimento
) {}