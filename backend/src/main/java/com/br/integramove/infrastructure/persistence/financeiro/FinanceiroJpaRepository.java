package com.br.integramove.infrastructure.persistence.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FinanceiroJpaRepository extends JpaRepository<FinanceiroEntity, UUID> {

    List<FinanceiroEntity> findByAluno_Id(UUID alunoId);
}