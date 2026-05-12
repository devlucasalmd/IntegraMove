package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, UUID> {

//    List<AvaliacaoEntity> findByAluno_Id(UUID alunoId);

    @Query("SELECT a FROM AvaliacaoEntity a WHERE a.aluno.id = :alunoId")
    List<AvaliacaoEntity> buscarPorAlunoId(@Param("alunoId") UUID alunoId);

    @Query("""
        SELECT a 
        FROM AvaliacaoEntity a 
        WHERE a.aluno.id = :alunoId
        AND a.id = :avaliacaoId
    """)
    Optional<AvaliacaoEntity> buscarPorAlunoIdEId(
            @Param("alunoId") UUID alunoId,
            @Param("avaliacaoId") UUID avaliacaoId
    );
}
