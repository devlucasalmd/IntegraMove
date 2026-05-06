package com.br.integramove.api.dto.response;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        boolean ativo
) {}