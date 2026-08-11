package com.br.integramove.application.despesa.inputs;

import com.br.integramove.domain.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PagarDespesaInput(

        @NotNull(message = "A data de pagamento é obrigatória.")
        @PastOrPresent(message = "A data de pagamento não pode ser futura.")
        LocalDate dataPagamento,

        @NotNull(message = "A forma de pagamento é obrigatória.")
        FormaPagamento formaPagamento
) {
}
