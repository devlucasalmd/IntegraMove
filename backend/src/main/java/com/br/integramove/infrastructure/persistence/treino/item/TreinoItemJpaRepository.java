package com.br.integramove.infrastructure.persistence.treino.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreinoItemJpaRepository extends JpaRepository<TreinoItemEntity, UUID> {
}
