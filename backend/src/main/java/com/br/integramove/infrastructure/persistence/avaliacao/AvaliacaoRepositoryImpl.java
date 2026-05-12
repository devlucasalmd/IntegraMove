package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Avaliacao> buscarPorAlunoIdEId(String alunoId, AvaliacaoId id){
        return jpa.buscarPorAlunoIdEId(
                UUID.fromString(alunoId),
                        id.getValue()
                )
                .map(AvaliacaoEntityMapper::toDomain);
    }

    @Override
    public List<Avaliacao> listarPorAlunoId(String alunoId) {
        return jpa.buscarPorAlunoId(UUID.fromString(alunoId))
                .stream()
                .map(AvaliacaoEntityMapper::toDomain)
                .toList();
    }
//
//    @Override
//    public List<Avaliacao> listarAvaliacoes(){
//        return jpa.findAll()
//                .stream()
//                .map(AvaliacaoEntityMapper::toDomain)
//                .toList();
//    }

}
