package com.br.integramove.application.pagamento.services;


import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.outputs.PagamentoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamentoAluno.Pagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPagamentosPorAluno {

    private final PagamentoRepository pagamentoRepository;

    public ListarPagamentosPorAluno(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<PagamentoOutput> executar(String alunoId) {
        return pagamentoRepository.listarPorAlunoId(AlunoId.from(alunoId))
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private PagamentoOutput toOutput(Pagamento pagamento) {
        return new PagamentoOutput(
                pagamento.getId().getValue().toString(),
                pagamento.getAlunoId().getValue().toString(),
                pagamento.getPlanoId().getValue().toString(),
                pagamento.getValor(),
                pagamento.getDataVencimento(),
                pagamento.getDataPagamento(),
                pagamento.getFormaPagamento() != null ? pagamento.getFormaPagamento().name() : null,
                pagamento.getStatus() != null ? pagamento.getStatus().name() : null,
                pagamento.getObservacoes()
        );
    }
}
