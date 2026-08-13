package com.br.integramove.application.venda.outputs;

import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.enums.StatusVenda;
import com.br.integramove.domain.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * periodicidade e contratoId não são campos de Venda: periodicidade é derivada do
 * Plano (quando tipo = PLANO) e contratoId referencia o Contrato eventualmente
 * gerado. Ambos expostos aqui para consumo futuro pelo módulo Financeiro sem
 * acoplar Venda à entidade Financeiro.
 */
public record VendaOutput(
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