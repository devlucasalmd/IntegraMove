package com.br.integramove.infrastructure.persistence.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, UUID> {

    List<PagamentoEntity> findByAluno_Id(UUID alunoId);

    Optional<PagamentoEntity> findByAluno_IdAndId(UUID alunoId, UUID pagamentoId);

}
