package com.br.integramove.domain.treino.exercicio;

import java.util.Objects;
import java.util.UUID;

public class ExercicioId {

    private final UUID value;

    private ExercicioId(UUID value){
        this.value = value;
    }

    public static ExercicioId novo(){ return new ExercicioId(UUID.randomUUID()); }

    public static ExercicioId from(String value){
        if (value == null) { throw new IllegalArgumentException("ExercicioId não pode ser nulo"); }
        return new ExercicioId(UUID.fromString(value));
    }

    public static ExercicioId of(UUID value) {
        return new ExercicioId(value);
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ExercicioId)) return false;
        ExercicioId exercicioId = (ExercicioId) obj;
        return value.equals(exercicioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
