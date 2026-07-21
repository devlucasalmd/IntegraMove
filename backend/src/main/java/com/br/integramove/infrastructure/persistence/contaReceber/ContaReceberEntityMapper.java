package com.br.integramove.infrastructure.persistence.contaReceber;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contasReceber.ContaReceber;
import com.br.integramove.domain.contasReceber.ContaReceberId;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContaReceberEntityMapper {

    public ContaReceberEntity toEntity(ContaReceber contaReceber) {
        AlunoEntity alunoEntity = null;
        PlanoEntity planoEntity = null;

        if (contaReceber.getAlunoId() != null) {
            alunoEntity = new AlunoEntity();
            alunoEntity.setId(contaReceber.getAlunoId().getValue());
        }

        if (contaReceber.getPlanoId() != null) {
            planoEntity = new PlanoEntity();
            planoEntity.setId(contaReceber.getPlanoId().getValue());
        }

        return new ContaReceberEntity(
                contaReceber.getId().getValue(),
                contaReceber.getDescricao(),
                contaReceber.getCategoria(),
                contaReceber.getValor(),
                contaReceber.getDataVencimento(),
                contaReceber.getDataRecebimento(),
                contaReceber.getFormaPagamento(),
                contaReceber.getStatus(),
                alunoEntity,
                planoEntity,
                contaReceber.getObservacoes()
        );
    }

    public ContaReceber toDomain(ContaReceberEntity entity) {
        AlunoId alunoId = null;
        PlanoId planoId = null;

        if (entity.getAluno() != null) {
            alunoId = AlunoId.from(entity.getAluno().getId().toString());
        }

        if (entity.getPlano() != null) {
            planoId = PlanoId.from(entity.getPlano().getId().toString());
        }

        return new ContaReceber(
                ContaReceberId.from(entity.getId()),
                entity.getDescricao(),
                entity.getCategoria(),
                entity.getValor(),
                entity.getDataVencimento(),
                entity.getDataRecebimento(),
                entity.getFormaPagamento(),
                entity.getStatus(),
                alunoId,
                planoId,
                entity.getObservacoes()
        );
    }
}
