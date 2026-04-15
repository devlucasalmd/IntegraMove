package com.br.integramove.api.dto.response;

import java.time.LocalDate;

public record TreinoAlunoResponseDTO(
        String id,
        String treinoId,
        String alunoId,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        Boolean ativo
) {
}
