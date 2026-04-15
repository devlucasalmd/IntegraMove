package com.br.integramove.infrastructure.persistence.treino.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreinoAlunoJpaRepository extends JpaRepository<TreinoAlunoEntity, UUID> {
}
