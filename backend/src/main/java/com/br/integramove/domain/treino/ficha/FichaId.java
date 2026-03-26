package com.br.integramove.domain.treino.ficha;

import java.util.Objects;
import java.util.UUID;

public class FichaId {

    private final UUID value;

    private FichaId(UUID value){
        this.value = value;
    }

    public static FichaId novo(){ return new FichaId(UUID.randomUUID()); }

    public static FichaId from(String value){
        if (value == null) { throw new IllegalArgumentException("FichaId não pode ser nulo"); }
        return new FichaId(UUID.fromString(value));
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FichaId)) return false;
        FichaId fichaId = (FichaId) obj;
        return value.equals(fichaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
