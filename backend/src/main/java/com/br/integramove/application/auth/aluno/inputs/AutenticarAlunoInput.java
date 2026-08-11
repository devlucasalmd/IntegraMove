package com.br.integramove.application.auth.aluno.inputs;

import jakarta.validation.constraints.NotBlank;

public record AutenticarAlunoInput(

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Senha é obrigatória")
        String senha

) {
}