package com.br.integramove.application.pagamento.services;

import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.PagamentoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamentoAluno.Pagamento;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class CriarPagamento {

    private final PagamentoRepository pagamentoRepository;

    public CriarPagamento(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoOutput executar(CriarPagamentoInput input) {
        Pagamento pagamento = Pagamento.criar(
                AlunoId.from(input.alunoId()),
                PlanoId.from(input.planoId()),
                input.valor(),
                input.dataVencimento(),
                input.observacoes()
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
