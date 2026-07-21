package com.br.integramove.application.usuario.services;

import com.br.integramove.application.usuario.UsuarioRepository;
import com.br.integramove.application.usuario.inputs.CriarUsuarioInput;
import com.br.integramove.application.usuario.outputs.CriarUsuarioOutput;
import com.br.integramove.domain.usuario.Usuario;
import com.br.integramove.domain.usuario.UsuarioId;
import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuario {

    private final UsuarioRepository repository;

    public CriarUsuario(UsuarioRepository repository){
        this.repository = repository;
    }
    public CriarUsuarioOutput criar(CriarUsuarioInput input) {

        if (repository.existePorCpf(input.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        Usuario usuario = new Usuario(
                UsuarioId.novo(),
                input.nome(),
                Cpf.of(input.cpf().toString()),
                input.telefone(),
                Email.of(input.email().toString()),
                input.senha(),
                input.perfil(),
                true
        );

        Usuario usuarioSalvo = repository.salvar(usuario);

        return new CriarUsuarioOutput(
                usuarioSalvo.getId().toString(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getCpf().getValue(),
                usuarioSalvo.getTelefone(),
                usuarioSalvo.getEmail().getValue(),
                usuarioSalvo.getPerfil(),
                usuarioSalvo.getAtivo()
        );
    }
}
