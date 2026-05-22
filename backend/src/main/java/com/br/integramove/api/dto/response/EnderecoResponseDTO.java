package com.br.integramove.api.dto.response;

public record EnderecoResponseDTO(
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro
) {}