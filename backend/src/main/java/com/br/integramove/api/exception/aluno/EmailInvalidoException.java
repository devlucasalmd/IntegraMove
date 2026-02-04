package com.br.integramove.api.exception.aluno;

public class EmailInvalidoException extends RuntimeException {

    public EmailInvalidoException() {
        super("Email invalido");
    }
}
