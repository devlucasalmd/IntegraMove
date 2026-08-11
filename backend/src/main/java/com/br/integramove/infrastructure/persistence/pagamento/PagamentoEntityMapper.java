package com.br.integramove.infrastructure.persistence.pagamento;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamentoAluno.Pagamento;
import com.br.integramove.domain.pagamentoAluno.PagamentoId;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoEntityMapper {

    public PagamentoEntity toEntity(Pagamento pagamento) {
        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(pagamento.getAlunoId().getValue());

        PlanoEntity planoEntity = new PlanoEntity();
        planoEntity.setId(pagamento.getPlanoId().getValue());

        return new PagamentoEntity(
                pagamento.getId().getValue(),
                alunoEntity,
                planoEntity,
                pagamento.getValor(),
                pagamento.getDataVencimento(),
                pagamento.getDataPagamento(),
                pagamento.getFormaPagamento(),
                pagamento.getStatus(),
                pagamento.getObservacoes()
        );
    }

    public Pagamento toDomain(PagamentoEntity entity) {
        return new Pagamento(
                PagamentoId.from(entity.getId().toString()),
                AlunoId.from(entity.getAluno().getId().toString()),
                PlanoId.from(entity.getPlano().getId().toString()),
                entity.getValor(),
                entity.getDataVencimento(),
                entity.getDataPagamento(),
                entity.getFormaPagamento(),
                entity.getStatus(),
                entity.getObservacoes()
        );
    }
}
