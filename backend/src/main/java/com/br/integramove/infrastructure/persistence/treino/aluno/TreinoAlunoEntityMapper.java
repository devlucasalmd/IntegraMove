package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.aluno.AlunoJpaRepository;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TreinoAlunoEntityMapper {

    private final AlunoJpaRepository alunoJpaRepository;
    private final TreinoJpaRepository treinoJpaRepository;

    public TreinoAlunoEntityMapper(
            AlunoJpaRepository alunoJpaRepository,
            TreinoJpaRepository treinoJpaRepository
    ) {
        this.alunoJpaRepository = alunoJpaRepository;
        this.treinoJpaRepository = treinoJpaRepository;
    }

    public TreinoAlunoEntity toEntity(TreinoAluno domain) {

        AlunoEntity aluno = alunoJpaRepository.findById(domain.getAlunoId().getValue())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        List<UUID> treinosIds = domain.getTreinosIds()
                .stream()
                .map(TreinoId::getValue)
                .toList();

        List<TreinoEntity> treinos = treinoJpaRepository.findAllById(treinosIds);

        if (treinos.size() != treinosIds.size()) {
            throw new RuntimeException("Um ou mais treinos não foram encontrados");
        }

        TreinoAlunoEntity entity = new TreinoAlunoEntity();
        entity.setId(domain.getId().getValue());
        entity.setAluno(aluno);
        entity.setNome(domain.getNome());
        entity.setDataInicio(domain.getDataInicio());
        entity.setDataFim(domain.getDataFim());
        entity.setAtivo(domain.isAtivo());
        entity.setTreinos(treinos);

        return entity;
    }

    public TreinoAluno toDomain(TreinoAlunoEntity entity) {

        List<TreinoId> treinosIds = entity.getTreinos()
                .stream()
                .map(treino -> TreinoId.of(treino.getId()))
                .toList();

        return new TreinoAluno(
                TreinoAlunoId.of(entity.getId()),
                AlunoId.of(entity.getAluno().getId()),
                treinosIds,
                entity.getNome(),
                entity.getDataInicio(),
                entity.getDataFim(),
                entity.isAtivo()
        );
    }
}