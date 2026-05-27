package com.br.integramove.application.treino.aluno.inputs;

import java.time.LocalDate;

public record CriarTreinoAlunoInput(
        String alunoId,
        String treinoId,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        Boolean ativo
) {
}
