package com.br.integramove.infrastructure.persistence.despesa;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DespesaJpaRepository extends JpaRepository<DespesaEntity, UUID> {
}
