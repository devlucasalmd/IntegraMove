package com.br.integramove.application.usuario.outputs;

import com.br.integramove.domain.enums.PerfilUsuario;

public record AtualizarUsuarioOutput(
        String id,
        String nome,
        String cpf,
        String telefone,
        String email,
        PerfilUsuario perfil,
        Boolean ativo
) {}
