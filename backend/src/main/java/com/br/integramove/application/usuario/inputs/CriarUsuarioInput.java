package com.br.integramove.application.usuario.inputs;

import com.br.integramove.domain.enums.PerfilUsuario;
import com.br.integramove.domain.valueobjects.Email;

public record CriarUsuarioInput(
        String nome,
        String cpf,
        String telefone,
        Email email,
        String senha,
        PerfilUsuario perfil
) {}
