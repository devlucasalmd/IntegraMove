package com.br.integramove.application.treino.aluno.inputs;

import java.time.LocalDate;
import java.util.List;

public record CriarTreinoAlunoInput(
        String alunoId,
        List<String> treinosIds,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        Boolean ativo
) {
}
