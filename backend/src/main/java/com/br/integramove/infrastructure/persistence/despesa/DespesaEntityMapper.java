package com.br.integramove.infrastructure.persistence.despesa;

import com.br.integramove.domain.despesa.Despesa;
import com.br.integramove.domain.despesa.DespesaId;
import org.springframework.stereotype.Component;

@Component
public class DespesaEntityMapper {

    public DespesaEntity toEntity(Despesa despesa) {
        return new DespesaEntity(
                despesa.getId().getValue(),
                despesa.getDescricao(),
                despesa.getCategoria(),
                despesa.getValor(),
                despesa.getDataVencimento(),
                despesa.getDataPagamento(),
                despesa.getFormaPagamento(),
                despesa.getStatus(),
                despesa.getFornecedor(),
                despesa.getObservacoes()
        );
    }

    public Despesa toDomain(DespesaEntity entity) {
        return new Despesa(
                DespesaId.from(entity.getId()),
                entity.getDescricao(),
                entity.getCategoria(),
                entity.getValor(),
                entity.getDataVencimento(),
                entity.getDataPagamento(),
                entity.getFormaPagamento(),
                entity.getStatus(),
                entity.getFornecedor(),
                entity.getObservacoes()
        );
    }
}
