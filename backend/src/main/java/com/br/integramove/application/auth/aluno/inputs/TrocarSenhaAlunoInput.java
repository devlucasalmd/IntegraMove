package com.br.integramove.application.auth.aluno.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TrocarSenhaAlunoInput(

        @NotBlank(message = "Aluno é obrigatório")
        String alunoId,

        @NotBlank(message = "Senha atual é obrigatória")
        String senhaAtual,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(
                min = 8,
                max = 100,
                message = "A senha deve possuir entre 8 e 100 caracteres."
        )
        String novaSenha

) {
}