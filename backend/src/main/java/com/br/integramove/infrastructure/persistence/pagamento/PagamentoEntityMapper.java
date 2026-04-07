package com.br.integramove.infrastructure.persistence.pagamento;

import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;

public class PagamentoEntityMapper {

    public static PagamentoEntity toEntity(Pagamento pagamento){

        PagamentoEntity pagamentoEntity = new PagamentoEntity();

        pagamentoEntity.setId(pagamento.getId().getValue());
        pagamentoEntity.setFormaPagamento(pagamento.getFormaPagamento());
        pagamentoEntity.setValor(pagamento.getValor());
        pagamentoEntity.setData(pagamento.getData());
        pagamentoEntity.setStatus(pagamento.getStatus());

        return pagamentoEntity;
    }

    public static Pagamento toDomain(PagamentoEntity pagamentoEntity){

        if(pagamentoEntity == null) return null;

        return new Pagamento(
                PagamentoId.from(pagamentoEntity.getId().toString()),
                pagamentoEntity.getFormaPagamento(),
                pagamentoEntity.getValor(),
                pagamentoEntity.getData(),
                pagamentoEntity.getStatus()
        );
    }
}
