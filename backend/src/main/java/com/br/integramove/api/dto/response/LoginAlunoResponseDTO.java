package com.br.integramove.api.dto.response;

public record LoginAlunoResponseDTO(
        String alunoId,
        String nome,
        boolean precisaTrocarSenha
) {}