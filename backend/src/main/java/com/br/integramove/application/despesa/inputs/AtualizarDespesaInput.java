package com.br.integramove.application.despesa.inputs;

import com.br.integramove.domain.enums.CategoriaDespesa;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarDespesaInput(
        @NotBlank(message = "A descrição é obrigatória.")
        @Size(min = 3, max = 150, message = "A descrição deve possuir entre 3 e 150 caracteres.")
        String descricao,

        @NotNull(message = "A categoria é obrigatória.")
        CategoriaDespesa categoria,

        @NotNull(message = "O valor é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "A data de vencimento é obrigatória.")
        LocalDate dataVencimento,

        @NotBlank(message = "O fornecedor é obrigatório.")
        @Size(min = 3, max = 150, message = "O fornecedor deve possuir entre 3 e 150 caracteres.")
        String fornecedor,

        @Size(max = 500, message = "As observações devem possuir no máximo 500 caracteres.")
        String observacoes

) {
}
