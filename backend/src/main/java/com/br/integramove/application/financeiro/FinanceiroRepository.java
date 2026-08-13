package com.br.integramove.application.financeiro;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.financeiro.FinanceiroId;

import java.util.List;
import java.util.Optional;

public interface FinanceiroRepository {

    Financeiro salvar(Financeiro financeiro);

    Optional<Financeiro> buscarPorId(FinanceiroId id);

    List<Financeiro> listarPorAlunoId(AlunoId alunoId);
}