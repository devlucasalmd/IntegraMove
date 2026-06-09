package com.br.integramove.application.pagamento.inputs;

import com.br.integramove.domain.pagamento.FormaPagamento;

import java.time.LocalDate;

public record PagarPagamentoInput(
        FormaPagamento formaPagamento,
        LocalDate dataPagamento
) {
}
