package com.br.integramove.application.treino.aluno.outputs;

import java.time.LocalDate;
import java.util.List;

public record CriarTreinoAlunoOutput(
        String id,
        String alunoId,
        List<String> treinosIds,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        Boolean ativo
) {}
