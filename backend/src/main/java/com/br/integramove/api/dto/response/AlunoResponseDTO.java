package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;

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