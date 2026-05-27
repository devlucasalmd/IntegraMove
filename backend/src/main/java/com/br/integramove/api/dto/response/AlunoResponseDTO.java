package com.br.integramove.api.dto.response;

import com.br.integramove.domain.aluno.Cpf;
import com.br.integramove.domain.aluno.Email;
import com.br.integramove.domain.aluno.Genero;
import com.br.integramove.domain.aluno.StatusAluno;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Genero genero,
        String telefone,
        String email,
        StatusAluno status,
        EnderecoResponseDTO enderecoDTO,
        String planoId,
        String nomePlano
) {}