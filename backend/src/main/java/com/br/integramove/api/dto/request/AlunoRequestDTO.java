package com.br.integramove.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nome,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        EnderecoDTO enderecoDTO,
        Boolean ativo
) {}
