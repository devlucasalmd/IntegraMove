package com.br.integramove.domain.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.util.Objects;
import java.util.UUID;

public class TreinoAlunoId {

    private final UUID value;

    private TreinoAlunoId(UUID value){
        this.value = value;
    }

    public static TreinoAlunoId novo(){ return new TreinoAlunoId(UUID.randomUUID()); }

    public static TreinoAlunoId from(String value){
        if (value == null) { throw new IllegalArgumentException("TreinoAlunoId não pode ser nulo"); }
        return new TreinoAlunoId(UUID.fromString(value));
    }

    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreinoId)) return false;
        TreinoAlunoId treinoAlunoId = (TreinoAlunoId) obj;
        return value.equals(treinoAlunoId.value);
    }

    public static TreinoAlunoId of(UUID value) {
        return new TreinoAlunoId(value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
