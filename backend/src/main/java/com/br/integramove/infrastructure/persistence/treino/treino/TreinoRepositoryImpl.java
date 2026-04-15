package com.br.integramove.infrastructure.persistence.treino.treino;

import com.br.integramove.application.treino.treino.TreinoRepository;
import com.br.integramove.domain.treino.treino.Treino;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TreinoRepositoryImpl implements TreinoRepository {

    private final TreinoJpaRepository jpa;

    public TreinoRepositoryImpl(TreinoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Treino salvar(Treino treino){

        TreinoEntity entity = TreinoEntityMapper.toEntity(treino);
        TreinoEntity saved = jpa.save(entity);

        return TreinoEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Treino> buscarPorId(TreinoId id){
        return jpa.findById(id.getValue())
                .map(TreinoEntityMapper::toDomain);
    }

    @Override
    public List<Treino> listarTodos(){
        return jpa.findAll()
                .stream()
                .map(TreinoEntityMapper::toDomain)
                .toList();
    }
}
