package com.br.integramove.api.exception.treino.exercicio;

public class TreinoItemNaoEncontradoExcpetion extends RuntimeException {

  public TreinoItemNaoEncontradoExcpetion(String id) {
        super("TreinoItem não encontrado: " + id);
    }
}
