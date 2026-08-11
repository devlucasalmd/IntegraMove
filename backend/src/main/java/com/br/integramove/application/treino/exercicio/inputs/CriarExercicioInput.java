package com.br.integramove.application.treino.exercicio.inputs;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarExercicioInput(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve possuir entre 3 e 100 caracteres.")
        String nome,

        @NotNull(message = "O grupo muscular é obrigatório.")
        GrupoMuscular grupoMuscular,

        @NotBlank(message = "A descrição é obrigatória.")
        @Size(min = 10, max = 1000, message = "A descrição deve possuir entre 10 e 1000 caracteres.")
        String descricao,

        @NotNull(message = "A intensidade é obrigatória.")
        Intensidade intensidade,

        @NotNull(message = "O status é obrigatório.")
        Boolean ativo
) {}
