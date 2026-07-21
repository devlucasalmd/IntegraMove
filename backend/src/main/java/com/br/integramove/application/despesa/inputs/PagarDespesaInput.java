package com.br.integramove.application.despesa.inputs;

import com.br.integramove.domain.enums.FormaPagamento;

import java.time.LocalDate;

public record PagarDespesaInput(
        LocalDate dataPagamento,
        FormaPagamento formaPagamento
) {
}
