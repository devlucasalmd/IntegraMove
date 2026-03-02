package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
    public Optional<Avaliacao> buscarPorId(AvaliacaoId id){
        return jpa.findById(id.getValue())
                .map(AvaliacaoEntityMapper::toDomain);
    }

//    @Override
//    public List<Avaliacao> listarAvaliacoes(){
//
//    }

}
