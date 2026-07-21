package com.br.integramove.api.exception.usuario;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.usuario.UsuarioId;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(UsuarioId id) {
        super("Aluno não encontrado " + id.toString());
    }
}
