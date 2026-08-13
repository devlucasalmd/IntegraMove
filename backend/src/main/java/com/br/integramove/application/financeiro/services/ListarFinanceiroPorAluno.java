package com.br.integramove.application.financeiro.services;

import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.financeiro.Financeiro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarFinanceiroPorAluno {

    private final FinanceiroRepository financeiroRepository;

    public ListarFinanceiroPorAluno(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    public List<FinanceiroOutput> listar(String alunoId) {

        List<Financeiro> cobrancas = financeiroRepository.listarPorAlunoId(AlunoId.from(alunoId));

        return cobrancas.stream()
                .map(financeiro -> new FinanceiroOutput(
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
                ))
                .toList();
    }
}