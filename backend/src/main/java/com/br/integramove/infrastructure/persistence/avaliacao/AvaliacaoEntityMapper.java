package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;

public class AvaliacaoEntityMapper {

    public static AvaliacaoEntity toEntity(Avaliacao avaliacao){
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();

        avaliacaoEntity.setId(avaliacao.getId().getValue());
        avaliacaoEntity.setDataAvaliacao(avaliacao.getDataAvaliacao());
        avaliacaoEntity.setRemadaBracoD((avaliacao.getRemadaBracoD()));
        avaliacaoEntity.setRemadaBracoE(avaliacao.getRemadaBracoE());
        avaliacaoEntity.setElevacaoLatD(avaliacao.getElevacaoLatD());
        avaliacaoEntity.setElevacaoLatE(avaliacao.getElevacaoLatE());
        avaliacaoEntity.setExtensaoJoelhoD(avaliacao.getExtensaoJoelhoD());
        avaliacaoEntity.setExtensaoJoelhoE(avaliacao.getExtensaoJoelhoE());
        avaliacaoEntity.setFlexaoJoelhoD(avaliacao.getFlexaoJoelhoD());
        avaliacaoEntity.setFlexaoJoelhoE(avaliacao.getFlexaoJoelhoE());
        avaliacaoEntity.setExtensaoQuadrilD(avaliacao.getExtensaoQuadrilD());
        avaliacaoEntity.setExtensaoQuadrilE(avaliacao.getExtensaoQuadrilE());

        return avaliacaoEntity;
    }

    public static Avaliacao toDomain(AvaliacaoEntity avaliacaoEntity){
        return new Avaliacao
                (
                AvaliacaoId.from(String.valueOf(avaliacaoEntity.getId())),
                avaliacaoEntity.getDataAvaliacao(),
                avaliacaoEntity.getRemadaBracoD(),
                avaliacaoEntity.getRemadaBracoE(),
                avaliacaoEntity.getElevacaoLatD(),
                avaliacaoEntity.getElevacaoLatE(),
                avaliacaoEntity.getExtensaoJoelhoD(),
                avaliacaoEntity.getExtensaoJoelhoE(),
                avaliacaoEntity.getFlexaoJoelhoD(),
                avaliacaoEntity.getFlexaoJoelhoE(),
                avaliacaoEntity.getExtensaoQuadrilD(),
                avaliacaoEntity.getExtensaoQuadrilE()
        );
    }
}
