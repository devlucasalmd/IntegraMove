package com.br.integramove.application.aluno.outputs;

public record EnderecoOutput(
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro
) {}
