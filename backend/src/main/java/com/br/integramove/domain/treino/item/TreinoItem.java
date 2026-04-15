package com.br.integramove.domain.treino.item;

import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.math.BigDecimal;


public class TreinoItem {

    private TreinoItemId id;
    private ExercicioId exercicioId;
    private Integer series;
    private Integer repeticoes;
    private BigDecimal carga;
    private Integer descanso;
    private Integer ordem;

    public TreinoItem(
            TreinoItemId id,
            ExercicioId exercicioId,
            Integer series,
            Integer repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
            this.id = id;
            this.exercicioId = exercicioId;
            this.series = series;
            this.repeticoes = repeticoes;
            this.carga = carga;
            this.descanso = descanso;
            this.ordem = ordem;
    }


    public TreinoItemId getId() {
        return id;
    }

    public void setId(TreinoItemId id) {
        this.id = id;
    }

    public ExercicioId getExercicioId() {
        return exercicioId;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public BigDecimal getCarga() {
        return carga;
    }

    public void setCarga(BigDecimal carga) {
        this.carga = carga;
    }

    public Integer getDescanso() {
        return descanso;
    }

    public void setDescanso(Integer descanso) {
        this.descanso = descanso;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public void atualizar(
            Integer series,
            Integer repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
        if (series != null) this.series = series;
        if (repeticoes != null) this.repeticoes = repeticoes;
        if (carga != null) this.carga = carga;
        if (descanso != null) this.descanso = descanso;
        if (ordem != null) this.ordem = ordem;
    }
}
