package com.br.integramove.api.dto.response;

public record AlunoResponseDTO(
        String id,
        String nome,
        String cpf,
        String email,
        boolean ativo
) {}