package com.br.integramove.application.aluno;

import com.br.integramove.api.dto.request.EnderecoDTO;

import java.time.LocalDate;

public record AtualizarAlunoInput(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        EnderecoDTO enderecoDTO,
        boolean ativo
) {}

