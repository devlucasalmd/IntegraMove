package com.br.integramove.domain.avaliacao;

import java.util.Objects;
import java.util.UUID;

public class AvalicaoId {

    private final UUID value;

    private AvalicaoId(UUID value) { this.value = value; }

    public static AvalicaoId novo(){ return new AvalicaoId(UUID.randomUUID()); }

    public static AvalicaoId from(String value){
        if (value == null) {throw new IllegalArgumentException("AvaliacaoId não pode ser nulo");}
        return new AvalicaoId(UUID.fromString(value));
    }

    public UUID getValue(){ return value; }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof AvalicaoId)) return false;
        AvalicaoId avalicaoId = (AvalicaoId) obj;
        return value.equals(avalicaoId.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }
}
