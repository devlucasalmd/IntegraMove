package com.br.integramove.infrastructure.persistence.aluno;

import com.br.integramove.domain.aluno.*;

public class AlunoEntityMapper {

    public static AlunoEntity toEntity(Aluno aluno) {
        AlunoEntity entity = new AlunoEntity();

        entity.setId(aluno.getId().getValue());
        entity.setNome(aluno.getNome());
        entity.setDataNascimento(aluno.getDataNascimento());
        entity.setCpf(aluno.getCpf().toString());
        entity.setGenero(aluno.getGenero().name());
        entity.setTelefone(aluno.getTelefone());
        entity.setEmail(aluno.getEmail().toString());
        entity.setAtivo(aluno.estaAtivo());

        Endereco e = aluno.getEndereco();
        entity.setCep(e.getCep());
        entity.setEstado(e.getEstado());
        entity.setCidade(e.getCidade());
        entity.setRua(e.getRua());
        entity.setNumero(e.getNumero());
        entity.setBairro(e.getBairro());

        return entity;
    }

    public static Aluno toDomain(AlunoEntity entity) {
        Endereco endereco = new Endereco(
                entity.getCep(),
                entity.getEstado(),
                entity.getCidade(),
                entity.getRua(),
                entity.getNumero(),
                entity.getBairro()
        );

        return new Aluno(
                AlunoId.from(String.valueOf(entity.getId())),
                entity.getNome(),
                entity.getDataNascimento(),
                new Cpf(entity.getCpf()),
                Genero.valueOf(entity.getGenero()),
                entity.getTelefone(),
                new Email(entity.getEmail()),
                endereco,
                entity.isAtivo()
        );
    }
}
