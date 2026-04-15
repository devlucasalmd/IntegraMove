package com.br.integramove.infrastructure.persistence.treino.treino;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreinoJpaRepository extends JpaRepository<TreinoEntity, UUID> {
}
