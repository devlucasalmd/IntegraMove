package com.br.integramove.infrastructure.persistence.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExercicioJpaRepository extends JpaRepository<ExercicioEntity, UUID> {

    List<ExercicioEntity> findByGrupoMuscular(GrupoMuscular grupoMuscular);
}
