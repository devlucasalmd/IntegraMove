package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.PerfilUsuario;

public record UsuarioResponseDTO(
        String id,
        String nome,
        String cpf,
        String telefone,
        String email,
        PerfilUsuario perfil,
        Boolean ativo
) {}
