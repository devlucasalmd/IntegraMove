package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TreinoAlunoRepositoryImpl implements TreinoAlunoRepository {

   private final TreinoAlunoJpaRepository jpa;

    public TreinoAlunoRepositoryImpl(TreinoAlunoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public TreinoAluno salvar(TreinoAluno treinoAluno){

        TreinoAlunoEntity entity = TreinoAlunoEntityMapper.toEntity(treinoAluno);
        TreinoAlunoEntity saved = jpa.save(entity);

        return TreinoAlunoEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<TreinoAluno> buscarPorId(TreinoAlunoId id){
        return jpa.findById(id.getValue())
                .map(TreinoAlunoEntityMapper::toDomain);
    }

    @Override
    public List<TreinoAluno> listarTodos(){
        return jpa.findAll()
                .stream()
                .map(TreinoAlunoEntityMapper::toDomain)
                .toList();
    }
}
