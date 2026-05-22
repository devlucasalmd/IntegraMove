package com.br.integramove.application.aluno.inputs;

public record EnderecoInput(
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro
) {
}
