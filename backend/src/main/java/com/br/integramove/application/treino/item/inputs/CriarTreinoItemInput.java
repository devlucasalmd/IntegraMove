package com.br.integramove.application.treino.item.inputs;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CriarTreinoItemInput(
        @NotBlank(message = "O treino é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Treino inválido."
        )
        String treinoId,

        @NotBlank(message = "O exercício é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Exercício inválido."
        )
        String exercicioId,

        @NotNull(message = "O número de séries é obrigatório.")
        @Min(value = 1, message = "O número de séries deve ser no mínimo 1.")
        @Max(value = 20, message = "O número de séries deve ser no máximo 20.")
        Integer series,

        @NotBlank(message = "As repetições são obrigatórias.")
        @Size(min = 1, max = 50, message = "As repetições devem possuir entre 1 e 20 caracteres.")
        String repeticoes,

        @NotNull(message = "A carga é obrigatória.")
        @DecimalMin(value = "0.0", inclusive = true, message = "A carga não pode ser negativa.")
        BigDecimal carga,

        @NotNull(message = "O tempo de descanso é obrigatório.")
        @Min(value = 0, message = "O descanso não pode ser negativo.")
        @Max(value = 1800, message = "O descanso deve ser de no máximo 1800 segundos.")
        Integer descanso,

        @NotNull(message = "A ordem é obrigatória.")
        @Min(value = 1, message = "A ordem deve ser maior que zero.")
        Integer ordem

) {
}
