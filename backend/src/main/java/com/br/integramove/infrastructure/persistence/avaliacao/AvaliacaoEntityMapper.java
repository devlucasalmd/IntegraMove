package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;

public class AvaliacaoEntityMapper {

    public static AvaliacaoEntity toEntity(Avaliacao avaliacao){
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();

        avaliacaoEntity.setId(avaliacao.getId());
        avaliacaoEntity.setDataAvaliacao(avaliacao.getDataAvaliacao());
        avaliacaoEntity.setCircuferencia((avaliacaoEntity.getCircuferencia()));
        avaliacaoEntity.setAltura(avaliacaoEntity.getAltura());
        avaliacaoEntity.setImc(avaliacaoEntity.getImc());
        avaliacaoEntity.setPeso(avaliacaoEntity.getPeso());
        avaliacaoEntity.setPercentualGordura(avaliacaoEntity.getPercentualGordura());

        return avaliacaoEntity;
    }

    public static Avaliacao toDomain(AvaliacaoEntity avaliacaoEntity){
        return new Avaliacao(
                avaliacaoEntity.getId(),
                avaliacaoEntity.getDataAvaliacao(),
                avaliacaoEntity.getCircuferencia(),
                avaliacaoEntity.getAltura(),
                avaliacaoEntity.getImc(),
                avaliacaoEntity.getPeso(),
                avaliacaoEntity.getPercentualGordura()
        );
    }
}
