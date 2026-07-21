package com.br.integramove.application.contaReceber.inputs;

import com.br.integramove.domain.enums.FormaPagamento;

import java.time.LocalDate;

public record ReceberContaReceberInput(
        LocalDate dataRecebimento,
        FormaPagamento formaPagamento
){
}
