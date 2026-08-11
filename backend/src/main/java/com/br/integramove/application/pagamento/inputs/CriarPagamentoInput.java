package com.br.integramove.application.pagamento.inputs;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public record CriarPagamentoInput(

        @NotBlank(message = "O aluno é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Aluno inválido."
        )
        String alunoId,

        @NotBlank(message = "O plano é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Plano inválido."
        )
        String planoId,

        @NotNull(message = "O valor é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "A data de vencimento é obrigatória.")
        LocalDate dataVencimento,

        @Size(max = 500, message = "As observações devem possuir no máximo 500 caracteres.")
        String observacoes
) {}