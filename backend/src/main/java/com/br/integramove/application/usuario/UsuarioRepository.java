package com.br.integramove.application.usuario;

import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(UsuarioId id);

    List<Usuario> listarTodos();

    boolean existePorCpf(String cpf);

}
