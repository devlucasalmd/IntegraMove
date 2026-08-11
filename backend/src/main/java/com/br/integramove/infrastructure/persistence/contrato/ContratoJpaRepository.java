package com.br.integramove.infrastructure.persistence.contrato;

import com.br.integramove.domain.enums.StatusContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContratoJpaRepository extends JpaRepository<ContratoEntity, UUID> {

    Optional<ContratoEntity> findByAluno_IdAndStatus(UUID alunoId, StatusContrato status);

    List<ContratoEntity> findByAluno_Id(UUID alunoId);

    @Query("""
        SELECT c
        FROM ContratoEntity c
        WHERE c.status = :status
        AND c.permiteRenovacaoAutomatica = false
        AND c.dataFim <= :dataReferencia
    """)
    List<ContratoEntity> buscarVencidosSemRenovacaoAutomatica(
            @Param("status") StatusContrato status,
            @Param("dataReferencia") LocalDate dataReferencia
    );
}