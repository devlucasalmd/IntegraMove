package com.br.integramove.domain.aluno;

import com.br.integramove.api.exception.aluno.CpfInvalidoException;

public class Cpf {

    private final String value;

    public Cpf(String value) {

        String numeros = value.replaceAll("\\D", "");

        if (!numeros.matches("\\d{11}")) { throw new CpfInvalidoException(); }
        this.value = formatar(numeros);
    }

    private String formatar(String numeros){
        return numeros.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})","$1.$2.$3-$4");
    }

    @Override
    public String toString() {
        return value;
    }

}
