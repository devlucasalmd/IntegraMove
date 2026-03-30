package com.br.integramove.infrastructure.persistence.treino.item;

import com.br.integramove.domain.treino.TreinoItem;
import com.br.integramove.domain.treino.TreinoItemId;
import com.br.integramove.domain.treino.exercicio.ExercicioId;

public class TreinoItemEntityMapper {


    public static TreinoItemEntity toEntity(TreinoItem domain) {

        if (domain == null) return null;

        TreinoItemEntity entity = new TreinoItemEntity();

        entity.setId(domain.getId().getValue());
        entity.setExercicioId(domain.getExercicioId().getValue());
        entity.setSeries(domain.getSeries());
        entity.setRepeticoes(domain.getRepeticoes());
        entity.setCarga(domain.getCarga());
        entity.setDescanso(domain.getDescanso());
        entity.setOrdem(domain.getOrdem());

        return entity;
    }

    public static TreinoItem toDomain(TreinoItemEntity entity) {

        if (entity == null) return null;

        return new TreinoItem(
                TreinoItemId.from(entity.getId().toString()),
                ExercicioId.from(entity.getExercicioId().toString()),
                entity.getSeries(),
                entity.getRepeticoes(),
                entity.getCarga(),
                entity.getDescanso(),
                entity.getOrdem()
        );
    }



}
