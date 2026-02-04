package com.br.integramove.application.aluno;

import com.br.integramove.domain.aluno.Genero;

import java.time.LocalDate;

public record AtualizarAlunoInput(
        String id,
        String nome,
        LocalDate dataNascimento,
        Genero genero,
        String telefone,
        String email,
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro
) {}

