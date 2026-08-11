package com.br.integramove.api.exception.plano;

public class ValorPlanoImutavelException extends RuntimeException {

    public ValorPlanoImutavelException() {
        super("O valor do plano não pode ser alterado após a criação. Crie um novo plano e inative o atual.");
    }
}