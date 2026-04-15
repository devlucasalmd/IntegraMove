package com.br.integramove.application.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;

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
