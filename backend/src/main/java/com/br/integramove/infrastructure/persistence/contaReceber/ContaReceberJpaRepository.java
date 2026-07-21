package com.br.integramove.infrastructure.persistence.contaReceber;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContaReceberJpaRepository extends JpaRepository<ContaReceberEntity, UUID> {

    List<ContaReceberEntity> findByAluno_Id(UUID alunoId);
}
