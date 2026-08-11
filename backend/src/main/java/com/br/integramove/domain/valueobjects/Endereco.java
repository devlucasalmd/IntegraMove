package com.br.integramove.domain.valueobjects;

import lombok.Getter;

import java.util.Objects;

public final class Endereco {

    private final String cep;
    private final String estado;
    private final String cidade;
    private final String rua;
    private final String numero;
    private final String bairro;


    private Endereco(
            String cep,
            String estado,
            String cidade,
            String rua,
            String numero,
            String bairro
    ) {

        this.cep = validar(cep, "CEP");
        this.estado = validar(estado, "Estado");
        this.cidade = validar(cidade, "Cidade");
        this.rua = validar(rua, "Rua");
        this.numero = validar(numero, "Número");
        this.bairro = validar(bairro, "Bairro");
    }


    public static Endereco of(
            String cep,
            String estado,
            String cidade,
            String rua,
            String numero,
            String bairro
    ) {

        return new Endereco(
                cep,
                estado,
                cidade,
                rua,
                numero,
                bairro
        );
    }


    private String validar(String valor, String campo) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    campo + " é obrigatório."
            );
        }

        return valor.trim();
    }


    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Endereco endereco)) {
            return false;
        }

        return cep.equals(endereco.cep)
                && numero.equals(endereco.numero)
                && rua.equals(endereco.rua);
    }


    @Override
    public int hashCode() {
        return Objects.hash(
                cep,
                rua,
                numero
        );
    }
}
