package com.br.integramove.application.usuario.services;

import com.br.integramove.api.exception.usuario.UsuarioNaoEncontradoException;
import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.application.usuario.inputs.DesativarUsuarioInput;
import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import org.springframework.stereotype.Service;

@Service
public class DesativarUsuario {

    private final UsuarioRepository usuarioRepository;

    public DesativarUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void desativar(DesativarUsuarioInput input) {

        UsuarioId id = UsuarioId.from(input.id());

        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        usuario.desativar();

        usuarioRepository.salvar(usuario);
    }
}
