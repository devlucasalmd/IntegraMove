package com.br.integramove.api.exception.treino.treino;

public class TreinoNaoEncontradoException extends RuntimeException {
    public TreinoNaoEncontradoException(String id) {
        super("Treino não encontrado: " + id);
    }
}
