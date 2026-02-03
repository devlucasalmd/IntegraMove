package com.br.integramove.domain.aluno;

public class Cpf {

    private final String value;

    public Cpf(String value) {
        if (!value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { throw new CpfInvalidoException(); }
        this.value = value;
    }
}
