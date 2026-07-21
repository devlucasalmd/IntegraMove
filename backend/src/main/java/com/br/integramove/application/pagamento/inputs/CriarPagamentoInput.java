package com.br.integramove.application.pagamento.inputs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CriarPagamentoInput(
        String alunoId,
        String planoId,
        BigDecimal valor,
        LocalDate dataVencimento,
        String observacoes
) {}
