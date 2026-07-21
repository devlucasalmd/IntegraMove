package com.br.integramove.infrastructure.persistence.usuario;

import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;

public class UsuarioEntityMapper {

    public static UsuarioEntity toEntity(Usuario usuario) {

        UsuarioEntity entity = new UsuarioEntity();

        if (usuario.getId() != null) {
            entity.setId(usuario.getId().getValue());
        }

        entity.setNome(usuario.getNome());
        entity.setCpf(usuario.getCpf().toString());
        entity.setTelefone(usuario.getTelefone());
        entity.setEmail(usuario.getEmail().toString());
        entity.setSenha(usuario.getSenha());
        entity.setPerfil(usuario.getPerfil());
        entity.setAtivo(usuario.getAtivo());

        return entity;
    }

    public static Usuario toDomain(UsuarioEntity entity) {

        return new Usuario(
                UsuarioId.from(entity.getId().toString()),
                entity.getNome(),
                new Cpf(entity.getCpf()),
                entity.getTelefone(),
                new Email(entity.getEmail()),
                entity.getSenha(),
                entity.getPerfil(),
                entity.getAtivo()
        );
    }

}
