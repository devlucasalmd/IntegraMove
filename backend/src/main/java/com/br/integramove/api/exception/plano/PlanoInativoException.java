package com.br.integramove.api.exception.plano;

public class PlanoInativoException extends RuntimeException {

    public PlanoInativoException() {
        super("Plano está inativo");
    }
}