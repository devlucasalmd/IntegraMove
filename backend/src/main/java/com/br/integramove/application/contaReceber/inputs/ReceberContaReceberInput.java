package com.br.integramove.application.contaReceber.inputs;

import com.br.integramove.domain.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ReceberContaReceberInput(
        @NotNull(message = "A data de recebimento é obrigatória.")
        @PastOrPresent(message = "A data de recebimento não pode ser futura.")
        LocalDate dataRecebimento,

        @NotNull(message = "A forma de pagamento é obrigatória.")
        FormaPagamento formaPagamento
){
}
