package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.FormaPagamento;

import java.time.LocalDate;

public record ReceberContaReceberRequestDTO(
        LocalDate dataRecebimento,
        FormaPagamento formaPagamento
) {
}
