package com.br.integramove.domain.financas.contasReceber;

import java.util.Objects;
import java.util.UUID;

public class ContaReceberId {

    private final UUID value;

    public ContaReceberId(UUID value) {
        this.value = value;
    }

    public static ContaReceberId novo() {
        return new ContaReceberId(UUID.randomUUID());
    }

    public static ContaReceberId from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("ContaReceberId não pode ser nulo");
        }

        return new ContaReceberId(UUID.fromString(value));
    }

    public static ContaReceberId from(UUID value) {
        return new ContaReceberId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ContaReceberId)) return false;
        ContaReceberId contaReceberId = (ContaReceberId) obj;
        return value.equals(contaReceberId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
