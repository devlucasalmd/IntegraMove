package com.br.integramove.domain.avaliacao;

import java.util.Objects;
import java.util.UUID;

public class AvaliacaoId {

    private final UUID value;

    private AvaliacaoId(UUID value) { this.value = value; }

    public static AvaliacaoId novo(){ return new AvaliacaoId(UUID.randomUUID()); }

    public static AvaliacaoId from(String value){
        if (value == null) {throw new IllegalArgumentException("AvaliacaoId não pode ser nulo");}
        return new AvaliacaoId(UUID.fromString(value));
    }

    public UUID getValue(){ return value; }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof AvaliacaoId)) return false;
        AvaliacaoId avaliacaoId = (AvaliacaoId) obj;
        return value.equals(avaliacaoId.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }
}
