package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;

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
        EnderecoOutput endereco,
        String senhaTemporaria
) {}
