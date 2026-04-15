package com.br.integramove.domain.treino.item;

import java.util.Objects;
import java.util.UUID;

public class TreinoItemId {

    private final UUID value;

    private TreinoItemId(UUID value){
        this.value = value;
    }

    public static TreinoItemId novo(){ return new TreinoItemId(UUID.randomUUID()); }

    public static TreinoItemId from(String value){
        if (value == null) { throw new IllegalArgumentException("FichaId não pode ser nulo"); }
        return new TreinoItemId(UUID.fromString(value));
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreinoItemId)) return false;
        TreinoItemId treinoItemId = (TreinoItemId) obj;
        return value.equals(treinoItemId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
