package com.br.integramove.infrastructure.persistence.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.exercicio.Intensidade;

public class ExercicioEntityMapper {

    public static ExercicioEntity toEntity(Exercicio exercicio){

        if (exercicio == null) return null;

        ExercicioEntity entity = new ExercicioEntity();

        entity.setId(exercicio.getId().getValue());
        entity.setNome(exercicio.getNome());
        entity.setGrupoMuscular(exercicio.getGrupoMuscular());
        entity.setDescricao(exercicio.getDescricao());
        entity.setIntensidade(exercicio.getIntensidade());
        entity.setAtivo(exercicio.getAtivo());


        return entity;
    }

    public static Exercicio toDomain(ExercicioEntity entity) {

        if (entity == null) return null;

        return new Exercicio(
                ExercicioId.from(entity.getId().toString()),
                entity.getNome(),
                entity.getGrupoMuscular(),
                entity.getDescricao(),
               entity.getIntensidade(),
                entity.getAtivo()
        );
    }
}
