package com.br.integramove.domain.valueobjects;

import com.br.integramove.api.exception.aluno.CpfInvalidoException;

import java.util.Objects;

public final class Cpf {

    private final String value;

    private Cpf(String value) {

        if (value == null || value.isBlank()) {
            throw new CpfInvalidoException();
        }

        String numeros = value.replaceAll("\\D", "");

        if (!numeros.matches("\\d{11}")) {
            throw new CpfInvalidoException();
        }

        this.value = formatar(numeros);
    }


    public static Cpf of(String value) {
        return new Cpf(value);
    }


    private String formatar(String numeros) {
        return numeros.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4"
        );
    }


    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Cpf cpf)) {
            return false;
        }

        return value.equals(cpf.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}