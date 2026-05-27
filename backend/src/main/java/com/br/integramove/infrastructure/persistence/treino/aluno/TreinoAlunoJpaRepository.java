package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TreinoAlunoJpaRepository extends JpaRepository<TreinoAlunoEntity, UUID> {
    List<TreinoAlunoEntity> findByAluno_Id(UUID alunoId);}
