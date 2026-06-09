package com.br.integramove.api.dto.response;

import java.time.LocalDate;
import java.util.List;

public record TreinoAlunoResponseDTO(
        String id,
        String alunoId,
        List<String> treinosIds,
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        Boolean ativo
) {
}
