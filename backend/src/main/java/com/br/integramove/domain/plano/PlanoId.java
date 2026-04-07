package com.br.integramove.domain.plano;

import java.util.Objects;
import java.util.UUID;

public class PlanoId {

    private final UUID value;

    public PlanoId(UUID value) { this.value = value; }

    public static PlanoId novo() { return  new PlanoId(UUID.randomUUID()); }

    public static PlanoId from(String value){
        if (value == null ) { throw new IllegalArgumentException("PlanoId não pode ser nulo"); }
        return new PlanoId(UUID.fromString(value));
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof  PlanoId)) return false;
        PlanoId planoId = (PlanoId) obj;
        return value.equals(planoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
