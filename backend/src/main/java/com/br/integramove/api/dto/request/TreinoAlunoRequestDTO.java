package com.br.integramove.api.dto.request;

import java.time.LocalDate;

public record TreinoAlunoRequestDTO(
        String id,
        String treinoId,
        String alunoId,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        Boolean ativo
) {
}
