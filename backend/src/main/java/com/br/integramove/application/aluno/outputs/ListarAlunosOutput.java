package com.br.integramove.application.aluno.outputs;

import com.br.integramove.domain.enums.StatusAluno;
import com.br.integramove.domain.enums.StatusPagamento;

public record ListarAlunosOutput(
        String id,
        String nome,
        String planoId,
        String nomePlano,
        StatusPagamento pagamento,
        StatusAluno status
) {}
