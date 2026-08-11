package com.br.integramove.infrastructure.persistence.aluno;

import com.br.integramove.domain.aluno.*;
import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;
import com.br.integramove.domain.valueobjects.Endereco;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;

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
        entity.setStatus(aluno.getStatus());
        entity.setSenhaHash(aluno.getSenhaHash());
        entity.setSenhaTemporaria(aluno.isSenhaTemporaria());

        Endereco e = aluno.getEndereco();
        entity.setCep(e.getCep());
        entity.setEstado(e.getEstado());
        entity.setCidade(e.getCidade());
        entity.setRua(e.getRua());
        entity.setNumero(e.getNumero());
        entity.setBairro(e.getBairro());

        if (aluno.getPlanoId() != null) {
            PlanoEntity planoEntity = new PlanoEntity();
            planoEntity.setId(aluno.getPlanoId().getValue());
            entity.setPlano(planoEntity);
        } else {
            entity.setPlano(null);
        }

        return entity;
    }

    public static Aluno toDomain(AlunoEntity entity) {
        Endereco endereco = Endereco.of(
                entity.getCep(),
                entity.getEstado(),
                entity.getCidade(),
                entity.getRua(),
                entity.getNumero(),
                entity.getBairro()
        );

        PlanoId planoId = null;

        if (entity.getPlano() != null) {
            planoId = PlanoId.from(entity.getPlano().getId().toString());
        }


        return new Aluno(
                AlunoId.from(entity.getId().toString()),
                entity.getNome(),
                entity.getDataNascimento(),
                Cpf.of(entity.getCpf()),
                Genero.valueOf(entity.getGenero()),
                entity.getTelefone(),
                Email.of(entity.getEmail()),
                entity.getStatus(),
                planoId,
                endereco,
                entity.getSenhaHash(),
                entity.isSenhaTemporaria()
                );
    }
}
