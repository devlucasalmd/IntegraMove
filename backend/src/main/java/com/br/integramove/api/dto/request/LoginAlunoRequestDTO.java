package com.br.integramove.api.dto.request;

public record LoginAlunoRequestDTO(
        String cpf,
        String senha
) {}