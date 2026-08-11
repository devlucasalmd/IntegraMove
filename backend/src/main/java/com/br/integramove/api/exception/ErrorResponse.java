package com.br.integramove.api.exception;

public record ErrorResponse(
        int status,
        String mensagem
) {
}
