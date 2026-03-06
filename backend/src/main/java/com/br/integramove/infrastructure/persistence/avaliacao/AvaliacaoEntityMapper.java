package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;

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

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(avaliacao.getAlunoId().getValue());
        avaliacaoEntity.setAluno(alunoEntity);
        return avaliacaoEntity;
    }

    public static Avaliacao toDomain(AvaliacaoEntity avaliacaoEntity){
        return new Avaliacao
                (
                AvaliacaoId.from(avaliacaoEntity.getId().toString()),
                AlunoId.from(avaliacaoEntity.getAluno().getId().toString()),
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
