package com.br.integramove.application.pagamento.services;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.CriarPagamentoOutput;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class CriarPagamento {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public CriarPagamento(
            PagamentoRepository pagamentoRepository,
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository
    ) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public CriarPagamentoOutput criar(CriarPagamentoInput input){

        Aluno aluno = alunoRepository.buscarPorId(AlunoId.from(input.alunoId()))
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (aluno.getPlanoId() == null) {
            throw new RuntimeException("Aluno não possui plano vinculado");
        }

        Plano plano = planoRepository.buscarPorId(aluno.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        if (!plano.estaAtivo()) {
            throw new RuntimeException("Plano está inativo");
        }

        FormaPagamento formaPagamento = FormaPagamento.valueOf(
                input.formaPagamento().toString().toUpperCase()
        );

        Pagamento pagamento = new Pagamento(
                PagamentoId.novo(),
                aluno.getId(),
                plano.getId(),
                plano.getValor(),
                input.dataPagamento(),
                input.dataVencimento(),
                formaPagamento,
                input.status()
        );

        Pagamento pagamentoSalvo = pagamentoRepository.salvar(pagamento);

        return new CriarPagamentoOutput(
                pagamentoSalvo.getId().getValue().toString(),
                pagamentoSalvo.getAlunoId().getValue().toString(),
                pagamentoSalvo.getPlanoId().getValue().toString(),
                pagamentoSalvo.getValor(),
                pagamentoSalvo.getDataPagamento(),
                pagamentoSalvo.getDataVencimento(),
                pagamentoSalvo.getFormaPagamento(),
                pagamentoSalvo.getStatus()
        );
    }
}
