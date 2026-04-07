package com.br.integramove.infrastructure.persistence.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, UUID> {
}
