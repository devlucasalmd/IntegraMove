package com.br.integramove.infrastructure.persistence.treino.item;

import com.br.integramove.application.treino.item.TreinoItemRepository;
import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TreinoItemRepositoryImpl implements TreinoItemRepository {

    private final TreinoItemJpaRepository jpa;


    public TreinoItemRepositoryImpl(TreinoItemJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public TreinoItem salvar(TreinoItem treinoItem){
        TreinoItemEntity entity = TreinoItemEntityMapper.toEntity(treinoItem);
        TreinoItemEntity saved = jpa.save(entity);

        return TreinoItemEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<TreinoItem> buscarPorId(TreinoItemId id){
        return jpa.findById(id.getValue())
                .map(TreinoItemEntityMapper::toDomain);
    }

    @Override
    public List<TreinoItem> listarTodos(){
        return jpa.findAll()
                .stream()
                .map(TreinoItemEntityMapper::toDomain)
                .toList();
    }


    @Override
    public List<TreinoItem> listarPorTreinoId(TreinoId treinoId) {
        return jpa.findByTreinoId(treinoId.getValue())
                .stream()
                .map(TreinoItemEntityMapper::toDomain)
                .toList();
    }

}
