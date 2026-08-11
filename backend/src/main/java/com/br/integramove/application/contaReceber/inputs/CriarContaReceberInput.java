package com.br.integramove.application.contaReceber.inputs;

import com.br.integramove.domain.enums.CategoriaContaReceber;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CriarContaReceberInput(

        String descricao,

        @NotNull(message = "A categoria é obrigatória.")
        CategoriaContaReceber categoria,

        @NotNull(message = "O valor é obrigatório.")
        @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "A data de vencimento é obrigatória.")
        LocalDate dataVencimento,

        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Aluno inválido."
        )
        String alunoId,

        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Plano inválido."
        )
        String planoId,

        @Size(max = 500, message = "As observações devem possuir no máximo 500 caracteres.")
        String observacoes

) {
}