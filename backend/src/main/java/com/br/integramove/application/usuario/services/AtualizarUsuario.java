package com.br.integramove.application.usuario.services;

import com.br.integramove.api.exception.usuario.UsuarioNaoEncontradoException;
import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.application.usuario.inputs.AtualizarUsuarioInput;
import com.br.integramove.application.usuario.outputs.AtualizarUsuarioOutput;
import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuario {

    private final UsuarioRepository usuarioRepository;

    public AtualizarUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AtualizarUsuarioOutput atualizar(AtualizarUsuarioInput input) {

        UsuarioId id = UsuarioId.from(input.id());

        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        usuario.atualizarDados(
                input.nome(),
                input.telefone(),
                input.email(),
                input.perfil(),
                input.ativo()
        );

        Usuario usuarioAtualizado = usuarioRepository.salvar(usuario);

        return new AtualizarUsuarioOutput(
                usuarioAtualizado.getId().getValue().toString(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getCpf().getValue(),
                usuarioAtualizado.getTelefone(),
                usuarioAtualizado.getEmail().getValue(),
                usuarioAtualizado.getPerfil(),
                usuarioAtualizado.getAtivo()
        );
    }
}
