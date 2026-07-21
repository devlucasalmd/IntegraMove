package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.PerfilUsuario;

public record UsuarioResumoResponseDTO(
        String id,
        String nome,
        PerfilUsuario perfil,
        Boolean ativo
) {}
