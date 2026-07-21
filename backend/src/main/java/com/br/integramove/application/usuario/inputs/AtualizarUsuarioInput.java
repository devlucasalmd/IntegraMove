package com.br.integramove.application.usuario.inputs;

import com.br.integramove.domain.enums.PerfilUsuario;
import com.br.integramove.domain.valueobjects.Email;

public record AtualizarUsuarioInput(
        String id,
        String nome,
        String telefone,
        Email email,
        PerfilUsuario perfil,
        Boolean ativo
) {}
