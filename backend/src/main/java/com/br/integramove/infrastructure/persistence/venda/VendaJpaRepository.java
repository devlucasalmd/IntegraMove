package com.br.integramove.infrastructure.persistence.venda;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendaJpaRepository extends JpaRepository<VendaEntity, UUID> {

    List<VendaEntity> findByAluno_Id(UUID alunoId);
}