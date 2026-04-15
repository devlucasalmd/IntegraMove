package com.br.integramove.application.treino.aluno;

import java.time.LocalDate;

public record ListarTreinoAlunoOutput(
        String id,
        String treinoId,
        String alunoId,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        Boolean ativo
) {
}
