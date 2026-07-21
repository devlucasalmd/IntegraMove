package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;

import java.time.LocalDate;

public record BuscarAlunoOutput(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Genero genero,
        String telefone,
        String email,
        StatusAluno status,
        EnderecoOutput endereco,
        String planoId,
        String nomePlano
) {}
