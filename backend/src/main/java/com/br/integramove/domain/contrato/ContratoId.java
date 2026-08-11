package com.br.integramove.domain.contrato;

import java.util.Objects;
import java.util.UUID;

public class ContratoId {

    private final UUID value;

    private ContratoId(UUID value) {
        this.value = value;
    }

    public static ContratoId novo() {
        return new ContratoId(UUID.randomUUID());
    }

    public static ContratoId from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("ContratoId não pode ser nulo");
        }
        return new ContratoId(UUID.fromString(value));
    }

    public static ContratoId of(UUID value) {
        return new ContratoId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ContratoId)) return false;
        ContratoId contratoId = (ContratoId) obj;
        return value.equals(contratoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}