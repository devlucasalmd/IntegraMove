package com.br.integramove.application.treino.aluno.outputs;

import java.time.LocalDate;
import java.util.List;

public record BuscarTreinoAlunoOutput(
        String id,
        String alunoId,
        List<String> treinosIds,
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        boolean ativo
) {}
