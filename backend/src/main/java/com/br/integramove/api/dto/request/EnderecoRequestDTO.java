package com.br.integramove.api.dto.request;

public record EnderecoRequestDTO(
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro
) {
}
