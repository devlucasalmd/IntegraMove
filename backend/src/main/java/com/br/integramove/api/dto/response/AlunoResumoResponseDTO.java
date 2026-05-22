package com.br.integramove.api.dto.response;

import com.br.integramove.domain.aluno.StatusAluno;
import com.br.integramove.domain.pagamento.StatusPagamento;

public record AlunoResumoResponseDTO(
        String id,
        String nome,
        String plano,
        StatusPagamento pagamento,
        StatusAluno status
) {
}
