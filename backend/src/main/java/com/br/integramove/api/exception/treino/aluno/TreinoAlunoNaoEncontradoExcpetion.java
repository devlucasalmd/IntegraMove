package com.br.integramove.api.exception.treino.aluno;

public class TreinoAlunoNaoEncontradoExcpetion extends RuntimeException {
    public TreinoAlunoNaoEncontradoExcpetion(String id) {
        super("Treino Aluno não encontrado: " + id);
    }
}
