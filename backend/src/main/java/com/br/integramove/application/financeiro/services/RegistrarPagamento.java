package com.br.integramove.application.financeiro.services;

import com.br.integramove.api.exception.financeiro.FinanceiroNaoEncontradoException;
import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.financeiro.inputs.RegistrarPagamentoInput;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.financeiro.FinanceiroId;
import org.springframework.stereotype.Service;

@Service
public class RegistrarPagamento {

    private final FinanceiroRepository financeiroRepository;

    public RegistrarPagamento(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    public FinanceiroOutput registrar(RegistrarPagamentoInput input) {

        FinanceiroId id = FinanceiroId.from(input.financeiroId());

        Financeiro financeiro = financeiroRepository.buscarPorId(id)
                .orElseThrow(() -> new FinanceiroNaoEncontradoException(id));

        financeiro.registrarPagamento(input.formaPagamento(), input.dataPagamento());

        Financeiro salvo = financeiroRepository.salvar(financeiro);

        return new FinanceiroOutput(
                salvo.getId().getValue().toString(),
                salvo.getAlunoId().getValue().toString(),
                salvo.getVendaId().getValue().toString(),
                salvo.getContratoId() != null ? salvo.getContratoId().getValue().toString() : null,
                salvo.getValor(),
                salvo.getNumeroParcela(),
                salvo.getTotalParcelas(),
                salvo.getDataVencimento(),
                salvo.getDataPagamento(),
                salvo.statusCalculado(),
                salvo.getFormaPagamento(),
                salvo.getCreatedAt(),
                salvo.getUpdatedAt()
        );
    }
}