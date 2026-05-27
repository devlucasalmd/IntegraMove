package com.br.integramove.application.pagamento.services;

import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.outputs.ListarPagamentosOutput;
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
                        pagamento.getAlunoId().getValue().toString(),
                        pagamento.getPlanoId().getValue().toString(),
                        pagamento.getValor(),
                        pagamento.getDataPagamento(),
                        pagamento.getDataVencimento(),
                        pagamento.getFormaPagamento(),
                        pagamento.getStatus()
                ))
                .toList();
    }
}
