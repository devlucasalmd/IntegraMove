package com.br.integramove.infrastructure.persistence.treino.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TreinoItemJpaRepository extends JpaRepository<TreinoItemEntity, UUID> {
    List<TreinoItemEntity> findByTreinoId(UUID treinoId);
}
