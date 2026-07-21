package com.br.integramove.application.treino.exercicio;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;

public record CriarExercicioInput(
         String nome,
         GrupoMuscular grupoMuscular,
         String descricao,
         Intensidade intensidade,
         Boolean ativo
) {}
