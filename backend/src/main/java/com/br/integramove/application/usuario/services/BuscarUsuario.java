package com.br.integramove.application.usuario.services;

import com.br.integramove.api.exception.usuario.UsuarioNaoEncontradoException;
import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.application.usuario.outputs.BuscarUsuarioOutput;
import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuario {
    private final UsuarioRepository usuarioRepository;

    public BuscarUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public BuscarUsuarioOutput buscar(String usuarioId) {

        UsuarioId id = UsuarioId.from(usuarioId);

        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        return new BuscarUsuarioOutput(
                usuario.getId().getValue().toString(),
                usuario.getNome(),
                usuario.getCpf().getValue(),
                usuario.getTelefone(),
                usuario.getEmail().getValue(),
                usuario.getPerfil(),
                usuario.getAtivo()
        );
    }
}
