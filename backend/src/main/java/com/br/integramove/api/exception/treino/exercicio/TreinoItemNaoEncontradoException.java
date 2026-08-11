package com.br.integramove.api.exception.treino.exercicio;

public class TreinoItemNaoEncontradoException extends RuntimeException {

    public TreinoItemNaoEncontradoException() {
        super("Item do treino não encontrado.");
    }

    public TreinoItemNaoEncontradoException(String id) {
        super("Item do treino não encontrado: " + id);
    }
}
