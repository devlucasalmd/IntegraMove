package com.br.integramove.api.exception.usuario;

import com.br.integramove.domain.usuario.UsuarioId;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }

    public UsuarioNaoEncontradoException(UsuarioId id) {
        super("Usuário não encontrado: " + id.getValue());
    }
}
