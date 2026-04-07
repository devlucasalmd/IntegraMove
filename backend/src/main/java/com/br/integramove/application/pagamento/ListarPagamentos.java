package com.br.integramove.application.pagamento;

import com.br.integramove.application.plano.ListarPlanosOutput;
import com.br.integramove.domain.pagamento.Pagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPagamentos {

    private final PagamentoRepository pagamentoRepository;

    public ListarPagamentos(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<ListarPagamentosOutput> listar(){

        List<Pagamento> pagamentos = pagamentoRepository.listarTodos();

        return pagamentos.stream()
                .map(pagamento -> new ListarPagamentosOutput(
                        pagamento.getId().getValue().toString(),
                        pagamento.getFormaPagamento().toString(),
                        pagamento.getValor(),
                        pagamento.getData(),
                        pagamento.getStatus()
                ))
                .toList();
    }
}
