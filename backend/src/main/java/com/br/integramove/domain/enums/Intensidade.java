package com.br.integramove.domain.enums;

public enum Intensidade {
    BAIXA,
    MEDIA,
    ALTA;

    public static Intensidade from(String value) {
        try {
            return Intensidade.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Intensidade inválida: " + value);
        }
    }

}
