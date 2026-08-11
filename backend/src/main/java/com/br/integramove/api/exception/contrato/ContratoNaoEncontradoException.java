package com.br.integramove.api.exception.contrato;

import com.br.integramove.domain.contrato.ContratoId;

public class ContratoNaoEncontradoException extends RuntimeException {

    public ContratoNaoEncontradoException(ContratoId id) {
        super("Contrato não encontrado: " + id.getValue());
    }
}