package com.br.integramove.application.financeiro.services;

import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.financeiro.inputs.GerarCobrancasInput;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.enums.TipoVenda;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.venda.VendaId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Toda Venda gera ao menos uma cobrança no Financeiro. Vendas de Plano (recorrente)
 * geram todas as parcelas do contrato de uma vez; as demais geram uma cobrança única.
 * Chamado diretamente por CriarVenda, no mesmo padrão usado para acionar CriarContrato.
 */
@Service
public class GerarCobrancasVenda {

    private final FinanceiroRepository financeiroRepository;

    public GerarCobrancasVenda(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    public List<FinanceiroOutput> gerar(GerarCobrancasInput input) {

        AlunoId alunoId = AlunoId.from(input.alunoId());
        VendaId vendaId = VendaId.from(input.vendaId());

        List<Financeiro> cobrancas = input.tipo() == TipoVenda.PLANO
                ? gerarParcelasPlano(alunoId, vendaId, input)
                : List.of(gerarCobrancaUnica(alunoId, vendaId, input));

        return cobrancas.stream()
                .map(financeiroRepository::salvar)
                .map(this::toOutput)
                .toList();
    }

    private List<Financeiro> gerarParcelasPlano(AlunoId alunoId, VendaId vendaId, GerarCobrancasInput input) {

        ContratoId contratoId = ContratoId.from(input.contratoId());
        LocalDate dataInicio = input.dataInicioContrato();
        LocalDate dataFim = input.dataFimContrato();

        List<LocalDate> vencimentos = input.periodicidade() == Periodicidade.DIARIA
                ? vencimentosDiarios(dataInicio, dataFim)
                : vencimentosPorMes(dataInicio, dataFim, input.diaVencimento(), stepMeses(input.periodicidade()));

        if (vencimentos.isEmpty()) {
            // Plano com duração curta demais para o diaVencimento escolhido cair dentro do
            // período: garante ao menos 1 parcela, cobrada no fim do contrato.
            vencimentos = List.of(dataFim);
        }

        int totalParcelas = vencimentos.size();

        List<Financeiro> parcelas = new ArrayList<>();
        for (int i = 0; i < totalParcelas; i++) {
            parcelas.add(Financeiro.criar(
                    alunoId,
                    vendaId,
                    contratoId,
                    input.valor(),
                    i + 1,
                    totalParcelas,
                    vencimentos.get(i)
            ));
        }
        return parcelas;
    }

    private Financeiro gerarCobrancaUnica(AlunoId alunoId, VendaId vendaId, GerarCobrancasInput input) {
        return Financeiro.criar(
                alunoId,
                vendaId,
                null,
                input.valor(),
                null,
                null,
                input.dataVenda()
        );
    }

    private List<LocalDate> vencimentosDiarios(LocalDate dataInicio, LocalDate dataFim) {
        List<LocalDate> vencimentos = new ArrayList<>();
        LocalDate vencimento = dataInicio;
        while (!vencimento.isAfter(dataFim)) {
            vencimentos.add(vencimento);
            vencimento = vencimento.plusDays(1);
        }
        return vencimentos;
    }

    private List<LocalDate> vencimentosPorMes(LocalDate dataInicio, LocalDate dataFim, int diaVencimento, int stepMeses) {
        List<LocalDate> vencimentos = new ArrayList<>();

        LocalDate primeiraParcela = diaVencimentoNoMes(dataInicio, diaVencimento);
        if (primeiraParcela.isBefore(dataInicio)) {
            primeiraParcela = diaVencimentoNoMes(dataInicio.plusMonths(1), diaVencimento);
        }

        LocalDate vencimento = primeiraParcela;
        while (!vencimento.isAfter(dataFim)) {
            vencimentos.add(vencimento);
            vencimento = diaVencimentoNoMes(vencimento.plusMonths(stepMeses), diaVencimento);
        }
        return vencimentos;
    }

    private LocalDate diaVencimentoNoMes(LocalDate referencia, int diaVencimento) {
        return referencia.withDayOfMonth(Math.min(diaVencimento, referencia.lengthOfMonth()));
    }

    private int stepMeses(Periodicidade periodicidade) {
        return switch (periodicidade) {
            case MENSAL -> 1;
            case TRIMESTRAL -> 3;
            case SEMESTRAL -> 6;
            case ANUAL -> 12;
            case DIARIA -> throw new IllegalStateException("DIARIA é tratada separadamente, por dia");
        };
    }

    private FinanceiroOutput toOutput(Financeiro financeiro) {
        return new FinanceiroOutput(
                financeiro.getId().getValue().toString(),
                financeiro.getAlunoId().getValue().toString(),
                financeiro.getVendaId().getValue().toString(),
                financeiro.getContratoId() != null ? financeiro.getContratoId().getValue().toString() : null,
                financeiro.getValor(),
                financeiro.getNumeroParcela(),
                financeiro.getTotalParcelas(),
                financeiro.getDataVencimento(),
                financeiro.getDataPagamento(),
                financeiro.statusCalculado(),
                financeiro.getFormaPagamento(),
                financeiro.getCreatedAt(),
                financeiro.getUpdatedAt()
        );
    }
}