package com.br.integramove.application.contaReceber.outputs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaReceberOutput (
        String id,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataRecebimento,
        String formaPagamento,
        String status,
        String alunoId,
        String planoId,
        String observacoes
){
}
