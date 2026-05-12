package com.br.integramove.infrastructure.persistence.treino.treino;

import com.br.integramove.domain.treino.treino.Treino;
import com.br.integramove.domain.treino.treino.TreinoId;
import com.br.integramove.infrastructure.persistence.treino.item.TreinoItemEntityMapper;

public class TreinoEntityMapper {

    public static TreinoEntity toEntity(Treino domain){

        if (domain == null) return null;

        TreinoEntity entity = new TreinoEntity();

        entity.setId(domain.getId().getValue());
        entity.setNome(domain.getNome());
        entity.setResponsavel(domain.getResponsavel());
        entity.setFuncionalidade(domain.getFuncionalidade());
        entity.setNivel(domain.getNivel());
        entity.setRepeticoes(entity.getRepeticoes());
        entity.setExercicios(entity.getExercicios());

        return entity;
    }

    public static Treino toDomain(TreinoEntity entity){

        if (entity == null) return null;

        return new Treino(
                TreinoId.from(entity.getId().toString()),
                entity.getNome(),
                entity.getResponsavel(),
                entity.getFuncionalidade(),
                entity.getNivel(),
                entity.getRepeticoes(),
                entity.getObservacoes()
        );
    }
}
