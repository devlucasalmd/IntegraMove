package com.br.integramove.application.treino.treino;

import com.br.integramove.application.treino.item.CriarTreinoItemInput;
import com.br.integramove.domain.treino.exercicio.GrupoMuscular;

import java.util.List;

public record CriarTreinoInput(
        String nome,
        String responsavel,
        String funcionalidade,
        String nivel,
        String repeticoes,
        String observacoes,
        GrupoMuscular grupoMuscular
) {
}
