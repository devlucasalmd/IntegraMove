package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;

import java.time.LocalDate;

/**
 * Resposta específica do endpoint de criação de aluno (POST /alunos).
 *
 * Diferente de {@link AlunoResponseDTO}, traz a senha temporária em texto puro
 * gerada no cadastro, para que a recepção possa repassá-la ao aluno.
 * Essa senha NUNCA é retornada em nenhum outro endpoint.
 */
public record AlunoCriadoResponseDTO(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Genero genero,
        String telefone,
        String email,
        StatusAluno status,
        EnderecoResponseDTO enderecoDTO,
        String planoId,
        String nomePlano,
        String senhaTemporaria
) {}