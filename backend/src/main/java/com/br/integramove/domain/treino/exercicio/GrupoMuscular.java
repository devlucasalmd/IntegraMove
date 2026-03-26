package com.br.integramove.domain.treino.exercicio;

public enum GrupoMuscular {
    PEITO,
    COSTAS,
    PERNAS,
    OMBRO,
    BICEPS,
    TRICEPS,
    TRAPEZIO,
    ANTEBRACO;

    public static GrupoMuscular from(String value) {
        try {
            return GrupoMuscular.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Grupo muscular inválido: " + value);
        }
    }
}
