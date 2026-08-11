package com.br.integramove.api.exception.auth;

public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException() {
        super("CPF ou senha inválidos.");
    }
}