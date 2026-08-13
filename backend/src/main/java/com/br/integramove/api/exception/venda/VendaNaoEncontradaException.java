package com.br.integramove.api.exception.venda;

import com.br.integramove.domain.venda.VendaId;

public class VendaNaoEncontradaException extends RuntimeException {

    public VendaNaoEncontradaException(VendaId id) {
        super("Venda não encontrada: " + id.getValue());
    }
}