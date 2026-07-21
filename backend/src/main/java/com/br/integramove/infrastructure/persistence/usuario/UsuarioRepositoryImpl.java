package com.br.integramove.infrastructure.persistence.usuario;

import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpa;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Usuario salvar(Usuario usuario) {

        UsuarioEntity entity = UsuarioEntityMapper.toEntity(usuario);
        UsuarioEntity usuarioSalvo = jpa.save(entity);

        return UsuarioEntityMapper.toDomain(usuarioSalvo);
    }

    @Override
    public Optional<Usuario> buscarPorId(UsuarioId id) {

        return jpa.findById(id.getValue())
                .map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {

        return jpa.findAll()
                .stream()
                .map(UsuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return jpa.existsByCpf(cpf);
    }

}
