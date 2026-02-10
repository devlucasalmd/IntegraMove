package com.br.integramove.api.dto.request;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String genero,
        String telefone,
        String email,
        String cep,
        String estado,
        String cidade,
        String rua,
        String numero,
        String bairro,
        Boolean ativo
) {}
