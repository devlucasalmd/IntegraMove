package com.br.integramove.api.exception.avaliacao;

public class AvaliacaoNaoEncontradaException extends RuntimeException{

    public AvaliacaoNaoEncontradaException(String id){
        super("Avaliacao nao encontrada: " + id);
    }
}
