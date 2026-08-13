package com.br.integramove.infrastructure.persistence.venda;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.venda.Venda;
import com.br.integramove.domain.venda.VendaId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import org.springframework.stereotype.Component;

@Component
public class VendaEntityMapper {

    public VendaEntity toEntity(Venda venda) {

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(venda.getAlunoId().getValue());

        PlanoEntity planoEntity = null;
        if (venda.getPlanoId() != null) {
            planoEntity = new PlanoEntity();
            planoEntity.setId(venda.getPlanoId().getValue());
        }

        return new VendaEntity(
                venda.getId().getValue(),
                alunoEntity,
                venda.getTipo(),
                planoEntity,
                venda.getDescricao(),
                venda.getValor(),
                venda.getDataVenda(),
                venda.getStatus(),
                venda.getCreatedAt(),
                venda.getUpdatedAt()
        );
    }

    public Venda toDomain(VendaEntity entity) {
        return new Venda(
                VendaId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                entity.getTipo(),
                entity.getPlano() != null ? PlanoId.from(entity.getPlano().getId().toString()) : null,
                entity.getDescricao(),
                entity.getValor(),
                entity.getDataVenda(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}