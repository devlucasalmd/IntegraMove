package com.br.integramove.api.exception.treino.exercicio;

public class ExercicioNaoEncontradoException extends RuntimeException{

    public ExercicioNaoEncontradoException(String id) {
        super("Exercício não encontrado: " + id);
    }
}
