package com.br.integramove.application.auth.aluno.outputs;

public record AutenticarAlunoOutput(
        String alunoId,
        String nome,
        boolean precisaTrocarSenha
) {
}