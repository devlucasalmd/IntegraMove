package com.br.integramove.domain.financeiro;

import java.util.Objects;
import java.util.UUID;

public class FinanceiroId {

    private final UUID value;

    private FinanceiroId(UUID value) {
        this.value = value;
    }

    public static FinanceiroId novo() {
        return new FinanceiroId(UUID.randomUUID());
    }

    public static FinanceiroId from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("FinanceiroId não pode ser nulo");
        }
        return new FinanceiroId(UUID.fromString(value));
    }

    public static FinanceiroId of(UUID value) {
        return new FinanceiroId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FinanceiroId)) return false;
        FinanceiroId financeiroId = (FinanceiroId) obj;
        return value.equals(financeiroId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}