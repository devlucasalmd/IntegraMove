package com.br.integramove.application.venda.inputs;

import com.br.integramove.domain.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * diaVencimento e permiteRenovacaoAutomatica não são persistidos em Venda —
 * existem apenas para alimentar a criação do Contrato quando tipo = PLANO.
 * diaVencimento é obrigatório nesse caso (sem valor inferido); permiteRenovacaoAutomatica
 * assume false quando omitido.
 */
public record CriarVendaInput(
        String alunoId,
        TipoVenda tipo,
        String planoId,
        String descricao,
        BigDecimal valor,
        LocalDate dataVenda,
        Integer diaVencimento,
        Boolean permiteRenovacaoAutomatica
) {}