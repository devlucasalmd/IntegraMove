package com.br.integramove.api.dto.response;

import com.br.integramove.domain.enums.StatusAluno;
import com.br.integramove.domain.enums.StatusPagamento;

public record AlunoResumoResponseDTO(
        String id,
        String nome,
        String planoId,
        String nomePlano,
        StatusPagamento pagamento,
        StatusAluno status
) {
}
