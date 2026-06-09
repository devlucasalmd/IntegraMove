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
    private final TreinoAlunoEntityMapper mapper;

    public TreinoAlunoRepositoryImpl(
            TreinoAlunoJpaRepository treinoAlunoJpaRepository,
            TreinoAlunoEntityMapper mapper
    ) {
        this.treinoAlunoJpaRepository = treinoAlunoJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreinoAluno salvar(TreinoAluno treinoAluno) {
        TreinoAlunoEntity entity = mapper.toEntity(treinoAluno);
        TreinoAlunoEntity saved = treinoAlunoJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TreinoAluno> buscarPorId(TreinoAlunoId id) {
        return treinoAlunoJpaRepository.findById(id.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<TreinoAluno> listarTodos() {
        return treinoAlunoJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<TreinoAluno> listarPorAlunoId(AlunoId alunoId) {
        return treinoAlunoJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}