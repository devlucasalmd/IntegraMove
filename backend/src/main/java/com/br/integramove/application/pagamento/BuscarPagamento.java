package com.br.integramove.application.pagamento;

import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarPagamento {

    private final PagamentoRepository pagamentoRepository;

    public BuscarPagamento(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public BuscarPagamentoOutput buscar(String id){

        PagamentoId pagamentoId = PagamentoId.from(id);

        Pagamento pagamento = pagamentoRepository.buscarPorId(pagamentoId).orElseThrow();

        return new BuscarPagamentoOutput(
                pagamento.getId().getValue().toString(),
                pagamento.getFormaPagamento().toString(),
                pagamento.getValor(),
                pagamento.getData(),
                pagamento.getStatus()
        );
    }
}
