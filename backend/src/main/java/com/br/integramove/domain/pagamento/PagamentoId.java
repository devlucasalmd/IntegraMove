package com.br.integramove.domain.pagamento;

import java.util.Objects;
import java.util.UUID;

public class PagamentoId {

    private final UUID value;

    public PagamentoId(UUID value) {
        this.value = value;
    }

    public static PagamentoId novo(){
        return new PagamentoId(UUID.randomUUID());
    }

    public static PagamentoId from(String value){
        if(value == null) { throw new IllegalArgumentException("PagamentoId não pode ser nulo");}
        return new PagamentoId(UUID.fromString(value));
    }

    public UUID getValue(){ return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PagamentoId)) return false;
        PagamentoId pagamentoId = (PagamentoId) obj;
        return value.equals(pagamentoId.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }
}
