package com.br.integramove.domain.treino.ficha;

import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.math.BigDecimal;


public class Ficha {

    private FichaId id;
    private ExercicioId exercicioId;
    private Integer series;
    private Integer repeticoes;
    private BigDecimal carga;
    private Integer descanso;
    private Integer ordem;
}
