package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.aluno.AlunoJpaRepository;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class TreinoAlunoRepositoryImpl implements TreinoAlunoRepository {

    private final TreinoAlunoJpaRepository treinoAlunoJpaRepository;
    private final AlunoJpaRepository alunoJpaRepository;
    private final TreinoJpaRepository treinoJpaRepository;

    public TreinoAlunoRepositoryImpl(
            TreinoAlunoJpaRepository treinoAlunoJpaRepository,
            AlunoJpaRepository alunoJpaRepository,
            TreinoJpaRepository treinoJpaRepository
    ) {
        this.treinoAlunoJpaRepository = treinoAlunoJpaRepository;
        this.alunoJpaRepository = alunoJpaRepository;
        this.treinoJpaRepository = treinoJpaRepository;
    }
    @Override
    public TreinoAluno salvar(TreinoAluno treinoAluno) {
        TreinoAlunoEntity entity = TreinoAlunoEntityMapper.toEntity(treinoAluno);

        System.out.println("========== DEBUG TREINO ALUNO ==========");
        System.out.println("ID da ficha: " + treinoAluno.getId().getValue());
        System.out.println("AlunoId recebido: " + treinoAluno.getAlunoId().getValue());
        System.out.println("TreinoId recebido: " + treinoAluno.getTreinoId().getValue());
        System.out.println("========================================");

        AlunoEntity alunoEntity = alunoJpaRepository.findById(
                treinoAluno.getAlunoId().getValue()
        ).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        TreinoEntity treinoEntity = treinoJpaRepository.findById(
                treinoAluno.getTreinoId().getValue()
        ).orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        entity.setAluno(alunoEntity);
        entity.setTreino(treinoEntity);

        TreinoAlunoEntity salvo = treinoAlunoJpaRepository.save(entity);

        return TreinoAlunoEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<TreinoAluno> buscarPorId(TreinoAlunoId id) {
        return treinoAlunoJpaRepository.findById(id.getValue())
                .map(TreinoAlunoEntityMapper::toDomain);
    }

    @Override
    public List<TreinoAluno> listarTodos() {
        return treinoAlunoJpaRepository.findAll()
                .stream()
                .map(TreinoAlunoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<TreinoAluno> listarPorAlunoId(AlunoId alunoId) {
        return treinoAlunoJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream()
                .map(TreinoAlunoEntityMapper::toDomain)
                .toList();
    }
}
