package com.br.integramove.infrastructure.persistence.treino.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TreinoItemJpaRepository extends JpaRepository<TreinoItemEntity, UUID> {
    List<TreinoItemEntity> findByTreinoId(UUID treinoId);

    @Query("""
        SELECT ti
        FROM TreinoItemEntity ti
        WHERE ti.treino.id = :treinoId
        ORDER BY ti.ordem ASC
    """)
    List<TreinoItemEntity> listarPorTreinoId(@Param("treinoId") UUID treinoId);
}
