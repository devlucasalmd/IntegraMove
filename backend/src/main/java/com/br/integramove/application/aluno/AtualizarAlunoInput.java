package com.br.integramove.application.aluno;

import java.time.LocalDate;

public record AtualizarAlunoInput(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro,
        boolean ativo
) {}

