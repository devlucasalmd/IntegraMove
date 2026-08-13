package com.br.integramove.application.financeiro.inputs;

import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * contratoId, periodicidade, dataInicioContrato, dataFimContrato e diaVencimento
 * só se aplicam quando tipo = PLANO — nos demais tipos, apenas dataVenda é usada
 * como vencimento da cobrança única.
 */
public record GerarCobrancasInput(
        String alunoId,
        String vendaId,
        TipoVenda tipo,
        BigDecimal valor,
        LocalDate dataVenda,
        String contratoId,
        Periodicidade periodicidade,
        LocalDate dataInicioContrato,
        LocalDate dataFimContrato,
        Integer diaVencimento
) {}