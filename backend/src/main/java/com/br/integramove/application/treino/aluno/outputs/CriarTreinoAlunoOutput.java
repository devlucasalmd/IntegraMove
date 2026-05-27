package com.br.integramove.application.treino.aluno.outputs;

import java.time.LocalDate;

public record CriarTreinoAlunoOutput(
        String id,
        String treinoId,
        String alunoId,
//      ProfessorId professorId;
        String nome,
        LocalDate dataInicio,
        Boolean ativo
) {}
