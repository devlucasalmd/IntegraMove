package com.br.integramove.application.treino.aluno.inputs;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CriarTreinoAlunoInput(

        @NotBlank(message = "O aluno é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Aluno inválido."
        )
        String alunoId,

        @NotEmpty(message = "É necessário informar pelo menos um treino.")
        List<
                @NotBlank(message = "O identificador do treino é obrigatório.")
                @Pattern(
                        regexp = "^[0-9a-fA-F\\-]{36}$",
                        message = "Treino inválido."
                )
                        String
                > treinosIds,

        @NotBlank(message = "O nome da ficha é obrigatório.")
        @Size(
                min = 2,
                max = 100,
                message = "O nome da ficha deve possuir entre 2 e 100 caracteres."
        )
        String nome,

        @NotNull(message = "A data de início é obrigatória.")
        LocalDate dataInicio,

        @NotNull(message = "A data de término é obrigatória.")
        LocalDate dataFim,

        @NotNull(message = "O status da ficha é obrigatório.")
        Boolean ativo

) {

    @AssertTrue(message = "A data de término deve ser igual ou posterior à data de início.")
    public boolean isPeriodoValido() {
        if (dataInicio == null || dataFim == null) {
            return true;
        }

        return !dataFim.isBefore(dataInicio);
    }
}