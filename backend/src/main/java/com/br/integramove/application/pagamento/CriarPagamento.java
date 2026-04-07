package com.br.integramove.application.pagamento;

import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import com.br.integramove.infrastructure.persistence.pagamento.FormaPagamento;
import org.springframework.stereotype.Service;

@Service
public class CriarPagamento {

    private final PagamentoRepository repository;

    public CriarPagamento(PagamentoRepository repository) {
        this.repository = repository;
    }

    public CriarPagamentoOutput criar(CriarPagamentoInput input){

        FormaPagamento formaPagamento = FormaPagamento.valueOf(input.formaPagamento().toUpperCase());

        Pagamento pagamento = new Pagamento(
                PagamentoId.novo(),
                formaPagamento,
                input.valor(),
                input.data(),
                input.status()
        );

        Pagamento pagamentoSalvo = repository.salvar(pagamento);

        return new CriarPagamentoOutput(
                pagamentoSalvo.getId().getValue().toString(),
                pagamentoSalvo.getFormaPagamento().toString(),
                pagamentoSalvo.getValor(),
                pagamentoSalvo.getData(),
                pagamentoSalvo.getStatus()
        );
    }
}
