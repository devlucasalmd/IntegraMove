package com.br.integramove.domain.despesa;

import java.util.Objects;
import java.util.UUID;

public class DespesaId {

    private final UUID value;

    public DespesaId(UUID value) {
        this.value = value;
    }

    public static DespesaId novo() {
        return new DespesaId(UUID.randomUUID());
    }

    public static DespesaId from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("DespesaId não pode ser nulo");
        }

        return new DespesaId(UUID.fromString(value));
    }

    public static DespesaId from(UUID value) {
        return new DespesaId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DespesaId)) return false;
        DespesaId despesaId = (DespesaId) obj;
        return value.equals(despesaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}