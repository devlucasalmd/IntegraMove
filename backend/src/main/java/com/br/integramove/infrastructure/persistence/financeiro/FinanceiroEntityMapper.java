package com.br.integramove.infrastructure.persistence.financeiro;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.financeiro.FinanceiroId;
import com.br.integramove.domain.venda.VendaId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.contrato.ContratoEntity;
import com.br.integramove.infrastructure.persistence.venda.VendaEntity;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroEntityMapper {

    public FinanceiroEntity toEntity(Financeiro financeiro) {

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(financeiro.getAlunoId().getValue());

        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setId(financeiro.getVendaId().getValue());

        ContratoEntity contratoEntity = null;
        if (financeiro.getContratoId() != null) {
            contratoEntity = new ContratoEntity();
            contratoEntity.setId(financeiro.getContratoId().getValue());
        }

        return new FinanceiroEntity(
                financeiro.getId().getValue(),
                alunoEntity,
                vendaEntity,
                contratoEntity,
                financeiro.getValor(),
                financeiro.getNumeroParcela(),
                financeiro.getTotalParcelas(),
                financeiro.getDataVencimento(),
                financeiro.getDataPagamento(),
                financeiro.getStatus(),
                financeiro.getFormaPagamento(),
                financeiro.getCreatedAt(),
                financeiro.getUpdatedAt()
        );
    }

    public Financeiro toDomain(FinanceiroEntity entity) {
        return new Financeiro(
                FinanceiroId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                VendaId.from(entity.getVenda().getId().toString()),
                entity.getContrato() != null ? ContratoId.from(entity.getContrato().getId().toString()) : null,
                entity.getValor(),
                entity.getNumeroParcela(),
                entity.getTotalParcelas(),
                entity.getDataVencimento(),
                entity.getDataPagamento(),
                entity.getStatus(),
                entity.getFormaPagamento(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}