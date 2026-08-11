package com.br.integramove.api.dto.request;

public record TrocarSenhaAlunoRequestDTO(
        String alunoId,
        String senhaAtual,
        String novaSenha
) {}