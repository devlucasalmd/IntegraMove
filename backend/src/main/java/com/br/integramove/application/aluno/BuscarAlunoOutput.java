package com.br.integramove.application.aluno;

import java.time.LocalDate;

public record BuscarAlunoOutput(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        boolean ativo
) {}
