package com.br.integramove.api.exception.plano;

import com.br.integramove.domain.plano.PlanoId;

public class PlanoNaoEncontradoException extends RuntimeException {

    public PlanoNaoEncontradoException(PlanoId id) {
        super("Plano não encontrado: " + id);
    }
}
