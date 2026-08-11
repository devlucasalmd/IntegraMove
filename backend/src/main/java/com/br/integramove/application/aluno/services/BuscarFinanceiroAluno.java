package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.BuscarFinanceiroAlunoOutput;
import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.enums.StatusFinanceiro;
import com.br.integramove.domain.enums.StatusPagamento;
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
    private final PagamentoRepository pagamentoRepository;

    public BuscarFinanceiroAluno(
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository,
            PagamentoRepository pagamentoRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.pagamentoRepository = pagamentoRepository;
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


        List<Pagamento> pagamentos =
                pagamentoRepository.listarPorAlunoId(aluno.getId());


        if (pagamentos.isEmpty()) {

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


        BigDecimal totalPago = pagamentos.stream()
                .filter(p -> p.getStatus() == StatusPagamento.PAGO)
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalEmAberto = pagamentos.stream()
                .filter(p ->
                        p.getStatus() == StatusPagamento.EM_ABERTO
                                ||
                                p.getStatus() == StatusPagamento.A_VENCER
                )
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalVencido = pagamentos.stream()
                .filter(p -> p.getStatus() == StatusPagamento.VENCIDO)
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        LocalDate ultimoPagamento = pagamentos.stream()
                .filter(p -> p.getStatus() == StatusPagamento.PAGO)
                .map(Pagamento::getDataPagamento)
                .filter(Objects::nonNull)
                .max(LocalDate::compareTo)
                .orElse(null);


        LocalDate proximoVencimento = pagamentos.stream()
                .filter(p ->
                        p.getStatus() == StatusPagamento.EM_ABERTO
                                ||
                                p.getStatus() == StatusPagamento.A_VENCER
                )
                .map(Pagamento::getDataVencimento)
                .filter(Objects::nonNull)
                .min(LocalDate::compareTo)
                .orElse(null);


        return new BuscarFinanceiroAlunoOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                plano.getId().getValue().toString(),
                plano.getNome(),
                plano.getValor(),
                definirStatusFinanceiro(pagamentos),
                totalPago,
                totalEmAberto,
                totalVencido,
                ultimoPagamento,
                proximoVencimento
        );
    }

    private StatusFinanceiro definirStatusFinanceiro(List<Pagamento> pagamentos) {
        boolean possuiVencido = pagamentos.stream()
                .anyMatch(p -> p.getStatus() == StatusPagamento.VENCIDO);

        if (possuiVencido) {
            return StatusFinanceiro.VENCIDO;
        }

        boolean possuiEmAberto = pagamentos.stream()
                .anyMatch(p -> p.getStatus() == StatusPagamento.EM_ABERTO);

        if (possuiEmAberto) {
            return StatusFinanceiro.EM_ABERTO;
        }

        return StatusFinanceiro.EM_DIA;
    }
}
