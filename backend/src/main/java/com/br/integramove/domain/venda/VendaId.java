package com.br.integramove.domain.venda;

import java.util.Objects;
import java.util.UUID;

public class VendaId {

    private final UUID value;

    private VendaId(UUID value) {
        this.value = value;
    }

    public static VendaId novo() {
        return new VendaId(UUID.randomUUID());
    }

    public static VendaId from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("VendaId não pode ser nulo");
        }
        return new VendaId(UUID.fromString(value));
    }

    public static VendaId of(UUID value) {
        return new VendaId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VendaId)) return false;
        VendaId vendaId = (VendaId) obj;
        return value.equals(vendaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}