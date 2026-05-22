package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.aluno.StatusAluno;
import com.br.integramove.domain.pagamento.StatusPagamento;

public record ListarAlunosOutput(
        String id,
        String nome,
        String plano,
        StatusPagamento pagamento,
        StatusAluno status
) {}
