package com.br.integramove.api.dto.request;

import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;
import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
public record AlunoRequestDTO(
        String nome,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,
        String cpf,
        Genero genero,
        String telefone,
        String email,
        StatusAluno status,
        String planoId,
        EnderecoRequestDTO enderecoDTO
) {}
