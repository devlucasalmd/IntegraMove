package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaRequestDTO(
        String alunoId,
        TipoVenda tipo,
        String planoId,
        String descricao,
        BigDecimal valor,
        LocalDate dataVenda,
        Integer diaVencimento,
        Boolean permiteRenovacaoAutomatica
) {}