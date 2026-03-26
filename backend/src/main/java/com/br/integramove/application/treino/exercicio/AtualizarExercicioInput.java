package com.br.integramove.application.treino.exercicio;

public record AtualizarExercicioInput(
        String id,
        String nome,
        String grupoMuscular,
        String descricao,
        String intensidade,
        Boolean ativo
) {
}
