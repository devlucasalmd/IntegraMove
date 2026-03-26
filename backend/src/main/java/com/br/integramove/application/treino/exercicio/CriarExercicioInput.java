package com.br.integramove.application.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import com.br.integramove.domain.treino.exercicio.Intensidade;

public record CriarExercicioInput(
         String nome,
         GrupoMuscular grupoMuscular,
         String descricao,
         Intensidade intensidade,
         Boolean ativo
) {}
