package com.br.integramove.application.aluno.inputs;

import com.br.integramove.domain.aluno.Cpf;
import com.br.integramove.domain.aluno.Email;
import com.br.integramove.domain.aluno.Genero;
import com.br.integramove.domain.aluno.StatusAluno;

import java.time.LocalDate;

public record AtualizarAlunoInput(
        String id,
        String nome,
        LocalDate dataNascimento,
        Cpf cpf,
        Genero genero,
        String telefone,
        Email email,
        StatusAluno status,
        EnderecoInput endereco
) {}

