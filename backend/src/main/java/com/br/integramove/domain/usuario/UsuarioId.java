package com.br.integramove.domain.usuario;

import java.util.Objects;
import java.util.UUID;

public class UsuarioId {

    private final UUID value;

    private UsuarioId(UUID value){
        this.value = value;
    }

    public static UsuarioId novo(){ return new UsuarioId(UUID.randomUUID()); }

    public static UsuarioId from(String value){
        if (value == null) { throw new IllegalArgumentException("UsuarioId não pode ser nulo"); }
        return new UsuarioId(UUID.fromString(value));
    }

    public static UsuarioId of(UUID value) {
        return new UsuarioId(value);
    }


    public UUID getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UsuarioId)) return false;
        UsuarioId usuarioId = (UsuarioId) obj;
        return value.equals(usuarioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
