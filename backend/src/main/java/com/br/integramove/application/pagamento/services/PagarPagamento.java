package com.br.integramove.application.pagamento.services;

import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.inputs.PagarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.PagamentoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import org.springframework.stereotype.Service;

@Service
public class PagarPagamento {

    private final PagamentoRepository pagamentoRepository;

    public PagarPagamento(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoOutput executar(
            String alunoId,
            String pagamentoId,
            PagarPagamentoInput input
    ) {
        Pagamento pagamento = pagamentoRepository
                .buscarPorAlunoIdEPagamentoId(
                        AlunoId.from(alunoId),
                        PagamentoId.from(pagamentoId)
                )
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado para este aluno"));

        pagamento.pagar(
                input.formaPagamento(),
                input.dataPagamento()
        );

        Pagamento pagamentoSalvo = pagamentoRepository.salvar(pagamento);

        return toOutput(pagamentoSalvo);
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