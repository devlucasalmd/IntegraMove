package com.br.integramove.infrastructure.persistence.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, UUID> {

    List<PagamentoEntity> findByAluno_Id(UUID alunoId);

    List<PagamentoEntity> findByPlano_Id(UUID planoId);
}
