package com.br.integramove.application.aluno.services;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.ListarAlunosOutput;
import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.enums.StatusPagamento;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.plano.Plano;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunos {


    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final FinanceiroRepository financeiroRepository;

    public ListarAlunos(
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository,
            FinanceiroRepository financeiroRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.financeiroRepository = financeiroRepository;
    }

    public List<ListarAlunosOutput> listar() {
        return alunoRepository.listarTodos()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private ListarAlunosOutput toOutput(Aluno aluno) {

        String planoId = null;
        String nomePlano = "Sem plano";

        if (aluno.getPlanoId() != null) {
            planoId = aluno.getPlanoId().getValue().toString();

            nomePlano = planoRepository.buscarPorId(aluno.getPlanoId())
                    .map(Plano::getNome)
                    .orElse("Plano não encontrado");
        }

        return new ListarAlunosOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                planoId,
                nomePlano,
                statusPagamento(aluno),
                aluno.getStatus()
        );
    }

    private StatusPagamento statusPagamento(Aluno aluno) {
        List<Financeiro> cobrancas = financeiroRepository.listarPorAlunoId(aluno.getId());

        boolean possuiAtrasado = cobrancas.stream()
                .anyMatch(c -> c.statusCalculado() == StatusPagamento.ATRASADO);
        if (possuiAtrasado) {
            return StatusPagamento.ATRASADO;
        }

        boolean possuiPendente = cobrancas.stream()
                .anyMatch(c -> c.statusCalculado() == StatusPagamento.PENDENTE);
        if (possuiPendente) {
            return StatusPagamento.PENDENTE;
        }

        return StatusPagamento.PAGO;
    }
}