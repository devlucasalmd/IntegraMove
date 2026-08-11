package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;

public class PlanoEntityMapper {

    public static PlanoEntity toEntity(Plano plano){

        PlanoEntity entity = new PlanoEntity();

        entity.setId(plano.getId().getValue());
        entity.setNome(plano.getNome());
        entity.setValor(plano.getValor());
        entity.setDescricao(plano.getDescricao());
        entity.setPeriodicidade(plano.getPeriodicidade());
        entity.setDuracaoDias(plano.getDuracaoDias());
        entity.setAtivo(plano.getAtivo());
        entity.setCreatedAt(plano.getCreatedAt());
        entity.setUpdatedAt(plano.getUpdatedAt());


        return entity;
    }

    public static Plano toDomain(PlanoEntity entity){

        return new Plano(
                PlanoId.from(entity.getId().toString()),
                entity.getNome(),
                entity.getValor(),
                entity.getDescricao(),
                entity.getPeriodicidade(),
                entity.getDuracaoDias(),
                entity.getAtivo(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}