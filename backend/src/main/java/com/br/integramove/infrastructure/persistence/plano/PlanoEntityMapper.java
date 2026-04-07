package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;

public class PlanoEntityMapper {

    public static PlanoEntity toEntity(Plano plano){

        PlanoEntity entity = new PlanoEntity();

        entity.setId(plano.getId().getValue());
        entity.setNome(plano.getNome());
        entity.setValor(plano.getValor());
        entity.setDescricao(plano.getDescricao());
        entity.setAtivo(plano.getAtivo());


        return entity;
    }

    public static Plano toDomain(PlanoEntity entity){

        return new Plano(
                PlanoId.from(entity.getId().toString()),
                entity.getNome(),
                entity.getValor(),
                entity.getDescricao(),
                entity.getAtivo()
        );
    }
}
