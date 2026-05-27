package com.br.integramove.application.pagamento.services;


import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.outputs.ListarPagamentosPorAlunoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPagamentosPorAluno {

    private final PagamentoRepository pagamentoRepository;

    public ListarPagamentosPorAluno(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<ListarPagamentosPorAlunoOutput> listar(String alunoId) {
        List<Pagamento> pagamentos = pagamentoRepository.listarPorAlunoId(
                AlunoId.from(alunoId)
        );

        return pagamentos.stream()
                .map(pagamento -> new ListarPagamentosPorAlunoOutput(
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
