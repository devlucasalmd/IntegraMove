package com.br.integramove.domain.treino;

import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.util.Objects;
import java.util.UUID;

public class TreinoId {

    private final UUID value;

    private TreinoId(UUID value){
        this.value = value;
    }

    public static TreinoId novo(){ return new TreinoId(UUID.randomUUID()); }

    public static TreinoId from(String value){
        if (value == null) { throw new IllegalArgumentException("TreinoId não pode ser nulo"); }
        return new TreinoId(UUID.fromString(value));
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreinoId)) return false;
        TreinoId treinoId = (TreinoId) obj;
        return value.equals(treinoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
