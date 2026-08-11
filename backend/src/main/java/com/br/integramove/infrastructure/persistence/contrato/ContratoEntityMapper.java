package com.br.integramove.infrastructure.persistence.contrato;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContratoEntityMapper {

    public ContratoEntity toEntity(Contrato contrato) {

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(contrato.getAlunoId().getValue());

        PlanoEntity planoEntity = new PlanoEntity();
        planoEntity.setId(contrato.getPlanoId().getValue());

        return new ContratoEntity(
                contrato.getId().getValue(),
                alunoEntity,
                planoEntity,
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getDiaVencimento(),
                contrato.getPermiteRenovacaoAutomatica(),
                contrato.getStatus(),
                contrato.getDocumentoUrl(),
                contrato.getCreatedAt(),
                contrato.getUpdatedAt()
        );
    }

    public Contrato toDomain(ContratoEntity entity) {
        return new Contrato(
                ContratoId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                PlanoId.from(entity.getPlano().getId().toString()),
                entity.getDataInicio(),
                entity.getDataFim(),
                entity.getDiaVencimento(),
                entity.getPermiteRenovacaoAutomatica(),
                entity.getStatus(),
                entity.getDocumentoUrl(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}