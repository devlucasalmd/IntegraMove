package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlanoRepositoryImpl implements PlanoRepository {

    private final PlanoJpaRepository jpa;


    public PlanoRepositoryImpl(PlanoJpaRepository jpa) { this.jpa = jpa; }

    @Override
    public Plano salvar(Plano plano){
        PlanoEntity entity = PlanoEntityMapper.toEntity(plano);
        PlanoEntity saved = jpa.save(entity);

        return PlanoEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Plano> buscarPorId(PlanoId id){
        return jpa.findById(id.getValue())
                .map(PlanoEntityMapper::toDomain);
    }

    @Override
    public List<Plano> buscarTodosPlanos(){
        return jpa.findAll()
                .stream()
                .map(PlanoEntityMapper::toDomain)
                .toList();
    }
}
