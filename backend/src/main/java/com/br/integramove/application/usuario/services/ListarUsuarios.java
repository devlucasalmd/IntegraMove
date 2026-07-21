package com.br.integramove.application.usuario.services;

import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.application.usuario.outputs.ListarUsuarioOutput;
import com.br.integramove.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuarios {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuarios(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<ListarUsuarioOutput> listar() {
        return usuarioRepository.listarTodos()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private ListarUsuarioOutput toOutput(Usuario usuario) {

        return new ListarUsuarioOutput(
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
