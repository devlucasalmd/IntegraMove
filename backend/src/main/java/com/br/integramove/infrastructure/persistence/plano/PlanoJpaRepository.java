package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.domain.plano.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoJpaRepository extends JpaRepository<PlanoEntity, UUID> {
}
