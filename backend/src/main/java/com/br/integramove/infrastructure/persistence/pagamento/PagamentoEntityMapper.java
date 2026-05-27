package com.br.integramove.infrastructure.persistence.pagamento;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;

public class PagamentoEntityMapper {
    public static PagamentoEntity toEntity(Pagamento pagamento) {
        PagamentoEntity entity = new PagamentoEntity();

        entity.setId(pagamento.getId().getValue());
        entity.setValor(pagamento.getValor());
        entity.setDataPagamento(pagamento.getDataPagamento());
        entity.setDataVencimento(pagamento.getDataVencimento());
        entity.setFormaPagamento(pagamento.getFormaPagamento());
        entity.setStatus(pagamento.getStatus());

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(pagamento.getAlunoId().getValue());
        entity.setAluno(alunoEntity);

        PlanoEntity planoEntity = new PlanoEntity();
        planoEntity.setId(pagamento.getPlanoId().getValue());
        entity.setPlano(planoEntity);

        return entity;
    }

    public static Pagamento toDomain(PagamentoEntity entity) {
        return new Pagamento(
                PagamentoId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                PlanoId.from(entity.getPlano().getId().toString()),
                entity.getValor(),
                entity.getDataPagamento(),
                entity.getDataVencimento(),
                entity.getFormaPagamento(),
                entity.getStatus()
        );
    }
}
