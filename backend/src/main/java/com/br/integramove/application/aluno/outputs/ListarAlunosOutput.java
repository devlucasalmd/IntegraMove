package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.aluno.StatusAluno;
import com.br.integramove.domain.pagamento.StatusPagamento;

public record ListarAlunosOutput(
        String id,
        String nome,
        String planoId,
        String nomePlano,
        StatusPagamento pagamento,
        StatusAluno status
) {}
