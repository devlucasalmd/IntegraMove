package com.br.integramove.api.exception.treino.aluno;

public class TreinoAlunoNaoEncontradoException extends RuntimeException {

    public TreinoAlunoNaoEncontradoException() {
        super("Treino do aluno não encontrado.");
    }
}
