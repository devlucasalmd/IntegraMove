package com.br.integramove.application.financeiro.inputs;

import com.br.integramove.domain.enums.FormaPagamento;

import java.time.LocalDate;

public record RegistrarPagamentoInput(
        String financeiroId,
        FormaPagamento formaPagamento,
        LocalDate dataPagamento
) {}