package com.br.integramove.application.aluno.inputs;

import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;
import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;

import java.time.LocalDate;

public record CriarAlunoInput(
    String nome,
    LocalDate dataNascimento,
    Cpf cpf,
    Genero genero,
    String telefone,
    Email email,
    StatusAluno status,
    String planoId,
    EnderecoInput endereco
) {}
