package com.br.integramove.api.exception.aluno;

import com.br.integramove.domain.aluno.AlunoId;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(AlunoId id) {
        super("Aluno não encontrado.");
    }

}
