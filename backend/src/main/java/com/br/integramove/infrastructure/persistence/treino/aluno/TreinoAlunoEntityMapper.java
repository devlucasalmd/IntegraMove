package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;
public class TreinoAlunoEntityMapper {

    public static TreinoAlunoEntity toEntity(TreinoAluno domain) {

        if (domain == null) return null;

        TreinoAlunoEntity entity = new TreinoAlunoEntity();

        entity.setId(domain.getId().getValue());
        entity.setNome(domain.getNome());
        entity.setDataInicio(domain.getDataInicio());
        entity.setAtivo(domain.isAtivo());

        return entity;
    }

    public static TreinoAluno toDomain(TreinoAlunoEntity entity) {

        if (entity == null) return null;

        return new TreinoAluno(
                TreinoAlunoId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                TreinoId.from(entity.getTreino().getId().toString()),
                entity.getNome(),
                entity.getDataInicio(),
                entity.isAtivo()
        );
    }
}