package com.br.integramove.api.dto.request;

import java.time.LocalDate;
import java.util.List;

public record TreinoAlunoRequestDTO(
        String alunoId,
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        Boolean ativo,
        List<String> treinosIds
) {
}
