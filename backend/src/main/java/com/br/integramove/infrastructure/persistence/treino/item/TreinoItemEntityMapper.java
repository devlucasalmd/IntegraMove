package com.br.integramove.infrastructure.persistence.treino.item;

import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.treino.TreinoId;
import com.br.integramove.infrastructure.persistence.treino.exercicio.ExercicioEntity;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;

public class TreinoItemEntityMapper {

    public static TreinoItemEntity toEntity(TreinoItem domain) {

        if (domain == null) {
            return null;
        }

        TreinoEntity treinoEntity = new TreinoEntity();
        treinoEntity.setId(domain.getTreinoId().getValue());

        ExercicioEntity exercicioEntity = new ExercicioEntity();
        exercicioEntity.setId(domain.getExercicioId().getValue());

        TreinoItemEntity entity = new TreinoItemEntity();

        entity.setId(domain.getId().getValue());
        entity.setTreino(treinoEntity);
        entity.setExercicio(exercicioEntity);
        entity.setSeries(domain.getSeries());
        entity.setRepeticoes(domain.getRepeticoes());
        entity.setCarga(domain.getCarga());
        entity.setDescanso(domain.getDescanso());
        entity.setOrdem(domain.getOrdem());

        return entity;
    }

    public static TreinoItem toDomain(TreinoItemEntity entity) {

        if (entity == null) {
            return null;
        }

        return new TreinoItem(
                TreinoItemId.from(entity.getId().toString()),
                ExercicioId.from(entity.getExercicio().getId().toString()),
                TreinoId.of(entity.getTreino().getId()),
                entity.getExercicio().getNome(),
                entity.getSeries(),
                entity.getRepeticoes(),
                entity.getCarga(),
                entity.getDescanso(),
                entity.getOrdem()
        );
    }
}