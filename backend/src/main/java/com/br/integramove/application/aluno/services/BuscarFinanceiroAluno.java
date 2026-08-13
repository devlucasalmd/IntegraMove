package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.BuscarFinanceiroAlunoOutput;
import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.StatusFinanceiro;
import com.br.integramove.domain.enums.StatusPagamento;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.plano.Plano;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class BuscarFinanceiroAluno {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final FinanceiroRepository financeiroRepository;

    public BuscarFinanceiroAluno(
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository,
            FinanceiroRepository financeiroRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.financeiroRepository = financeiroRepository;
    }

    public BuscarFinanceiroAlunoOutput buscar(String alunoId) {

        AlunoId id = AlunoId.from(alunoId);

        Aluno aluno = alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));


        if (aluno.getPlanoId() == null) {

            return new BuscarFinanceiroAlunoOutput(
                    aluno.getId().getValue().toString(),
                    aluno.getNome(),
                    null,
                    null,
                    BigDecimal.ZERO,
                    StatusFinanceiro.SEM_PLANO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    null,
                    null
            );
        }


        Plano plano = planoRepository.buscarPorId(aluno.getPlanoId())
                .orElseThrow(
                        () -> new PlanoNaoEncontradoException(aluno.getPlanoId())
                );


        List<Financeiro> cobrancas =
                financeiroRepository.listarPorAlunoId(aluno.getId());


        if (cobrancas.isEmpty()) {

            return new BuscarFinanceiroAlunoOutput(
                    aluno.getId().getValue().toString(),
                    aluno.getNome(),
                    plano.getId().getValue().toString(),
                    plano.getNome(),
                    plano.getValor(),
                    StatusFinanceiro.SEM_PAGAMENTOS,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    null,
                    null
            );
        }


        BigDecimal totalPago = cobrancas.stream()
                .filter(c -> c.getStatus() == StatusPagamento.PAGO)
                .map(Financeiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalEmAberto = cobrancas.stream()
                .filter(c -> c.statusCalculado() == StatusPagamento.PENDENTE)
                .map(Financeiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalVencido = cobrancas.stream()
                .filter(c -> c.statusCalculado() == StatusPagamento.ATRASADO)
                .map(Financeiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        LocalDate ultimoPagamento = cobrancas.stream()
                .filter(c -> c.getStatus() == StatusPagamento.PAGO)
                .map(Financeiro::getDataPagamento)
                .filter(Objects::nonNull)
                .max(LocalDate::compareTo)
                .orElse(null);


        LocalDate proximoVencimento = cobrancas.stream()
                .filter(c -> c.statusCalculado() == StatusPagamento.PENDENTE)
                .map(Financeiro::getDataVencimento)
                .filter(Objects::nonNull)
                .min(LocalDate::compareTo)
                .orElse(null);


        return new BuscarFinanceiroAlunoOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                plano.getId().getValue().toString(),
                plano.getNome(),
                plano.getValor(),
                definirStatusFinanceiro(cobrancas),
                totalPago,
                totalEmAberto,
                totalVencido,
                ultimoPagamento,
                proximoVencimento
        );
    }

    private StatusFinanceiro definirStatusFinanceiro(List<Financeiro> cobrancas) {
        boolean possuiAtrasado = cobrancas.stream()
                .anyMatch(c -> c.statusCalculado() == StatusPagamento.ATRASADO);

        if (possuiAtrasado) {
            return StatusFinanceiro.VENCIDO;
        }

        boolean possuiEmAberto = cobrancas.stream()
                .anyMatch(c -> c.statusCalculado() == StatusPagamento.PENDENTE);

        if (possuiEmAberto) {
            return StatusFinanceiro.EM_ABERTO;
        }

        return StatusFinanceiro.EM_DIA;
    }
}