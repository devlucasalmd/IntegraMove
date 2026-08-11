package com.br.integramove.application.treino.treino.inputs;

import com.br.integramove.domain.enums.GrupoMuscular;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarTreinoInput(

        @NotBlank(message = "O nome do treino é obrigatório.")
        @Size(
                min = 3,
                max = 100,
                message = "O nome do treino deve possuir entre 3 e 100 caracteres."
        )
        String nome,

        @NotBlank(message = "O responsável é obrigatório.")
        @Size(
                min = 3,
                max = 100,
                message = "O responsável deve possuir entre 3 e 100 caracteres."
        )
        String responsavel,

        @NotBlank(message = "A funcionalidade é obrigatória.")
        @Size(
                min = 3,
                max = 150,
                message = "A funcionalidade deve possuir entre 3 e 150 caracteres."
        )
        String funcionalidade,

        @NotBlank(message = "O nível é obrigatório.")
        @Size(
                min = 3,
                max = 50,
                message = "O nível deve possuir entre 3 e 50 caracteres."
        )
        String nivel,

        @NotBlank(message = "As repetições são obrigatórias.")
        @Size(
                min = 1,
                max = 50,
                message = "As repetições devem possuir no máximo 50 caracteres."
        )
        String repeticoes,

        @Size(
                max = 500,
                message = "As observações devem possuir no máximo 500 caracteres."
        )
        String observacoes,

        @NotNull(message = "O grupo muscular é obrigatório.")
        GrupoMuscular grupoMuscular
) {
}
