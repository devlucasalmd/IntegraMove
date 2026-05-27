package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.aluno.Cpf;
import com.br.integramove.domain.aluno.Email;
import com.br.integramove.domain.aluno.Genero;
import com.br.integramove.domain.aluno.StatusAluno;

import java.time.LocalDate;

public record CriarAlunoOutput(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Genero genero,
        String telefone,
        String email,
        StatusAluno status,
        String planoId,
        EnderecoOutput endereco
) {}
