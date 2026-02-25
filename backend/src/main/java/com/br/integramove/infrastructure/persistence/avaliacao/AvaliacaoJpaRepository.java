package com.br.integramove.infrastructure.persistence.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, Long> {
}
