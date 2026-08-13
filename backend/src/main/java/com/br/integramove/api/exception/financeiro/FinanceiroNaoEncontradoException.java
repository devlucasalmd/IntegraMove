package com.br.integramove.api.exception.financeiro;

import com.br.integramove.domain.financeiro.FinanceiroId;

public class FinanceiroNaoEncontradoException extends RuntimeException {

    public FinanceiroNaoEncontradoException(FinanceiroId id) {
        super("Cobrança não encontrada: " + id.getValue());
    }
}