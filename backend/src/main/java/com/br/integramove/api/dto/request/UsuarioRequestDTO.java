package com.br.integramove.api.dto.request;

import com.br.integramove.domain.enums.PerfilUsuario;
import com.br.integramove.domain.valueobjects.Email;

public record UsuarioRequestDTO(
        String nome,
        String cpf,
        String telefone,
        String email,
        String senha,
        PerfilUsuario perfil,
        Boolean ativo
) {
}
