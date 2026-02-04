package com.br.integramove.api.exception.aluno;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("CPF invalido");
    }
}
