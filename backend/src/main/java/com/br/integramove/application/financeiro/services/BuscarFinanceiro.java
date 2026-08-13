package com.br.integramove.application.financeiro.services;

import com.br.integramove.api.exception.financeiro.FinanceiroNaoEncontradoException;
import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.financeiro.FinanceiroId;
import org.springframework.stereotype.Service;

@Service
public class BuscarFinanceiro {

    private final FinanceiroRepository financeiroRepository;

    public BuscarFinanceiro(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    public FinanceiroOutput buscar(String id) {

        FinanceiroId financeiroId = FinanceiroId.from(id);

        Financeiro financeiro = financeiroRepository.buscarPorId(financeiroId)
                .orElseThrow(() -> new FinanceiroNaoEncontradoException(financeiroId));

        return new FinanceiroOutput(
                financeiro.getId().getValue().toString(),
                financeiro.getAlunoId().getValue().toString(),
                financeiro.getVendaId().getValue().toString(),
                financeiro.getContratoId() != null ? financeiro.getContratoId().getValue().toString() : null,
                financeiro.getValor(),
                financeiro.getNumeroParcela(),
                financeiro.getTotalParcelas(),
                financeiro.getDataVencimento(),
                financeiro.getDataPagamento(),
                financeiro.statusCalculado(),
                financeiro.getFormaPagamento(),
                financeiro.getCreatedAt(),
                financeiro.getUpdatedAt()
        );
    }
}