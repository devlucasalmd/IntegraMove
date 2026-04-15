package com.br.integramove.domain.aluno;

import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.util.Objects;
import java.util.UUID;

public class AlunoId {

    private final UUID value;

    private AlunoId(UUID value){
        this.value = value;
    }

    public static AlunoId novo(){ return new AlunoId(UUID.randomUUID()); }

    public static AlunoId from(String value){
        if (value == null) { throw new IllegalArgumentException("AlunoId não pode ser nulo"); }
        return new AlunoId(UUID.fromString(value));
    }

    public static AlunoId of(UUID value) {
        return new AlunoId(value);
    }


    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AlunoId)) return false;
        AlunoId alunoId = (AlunoId) obj;
        return value.equals(alunoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
