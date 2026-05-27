package com.br.integramove.api.dto.request;

import com.br.integramove.domain.aluno.Cpf;
import com.br.integramove.domain.aluno.Email;
import com.br.integramove.domain.aluno.Genero;
import com.br.integramove.domain.aluno.StatusAluno;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nome,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,
        Cpf cpf,
        Genero genero,
        String telefone,
        Email email,
        StatusAluno status,
        String planoId,
        EnderecoRequestDTO enderecoDTO
) {}
