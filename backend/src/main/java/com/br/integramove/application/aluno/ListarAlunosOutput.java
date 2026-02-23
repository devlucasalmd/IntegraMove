package com.br.integramove.application.aluno;

import java.util.UUID;

public record ListarAlunosOutput(
        String id,
        String nome,
        String cpf,
        String email,
        boolean ativo
) {}
