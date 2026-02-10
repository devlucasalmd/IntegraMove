package com.br.integramove.infrastructure.persistence.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoJpaRepository extends JpaRepository<AlunoEntity, UUID> {
    boolean existsByCpf(String cpf);
}
