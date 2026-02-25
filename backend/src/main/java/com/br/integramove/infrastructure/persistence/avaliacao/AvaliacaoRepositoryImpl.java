package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import com.br.integramove.domain.avaliacao.Avaliacao;

import java.util.List;
import java.util.Optional;

public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    private final AvaliacaoJpaRepository jpa;

    public AvaliacaoRepositoryImpl(AvaliacaoJpaRepository jpa){
        this.jpa = jpa;
    }

    @Override
    public void salvar(Avaliacao avaliacao){
        AvaliacaoEntity entity = AvaliacaoEntityMapper.toEntity(avaliacao);
        jpa.save(entity);
    }

    @Override
    public Optional<Avaliacao> buscarPorId(Avaliacao id){
        return jpa.findById(id.getId())
                .map(AvaliacaoEntityMapper::toDomain);
    }

//    @Override
//    public List<Avaliacao> listarAvaliacoes(){
//
//    }

}
