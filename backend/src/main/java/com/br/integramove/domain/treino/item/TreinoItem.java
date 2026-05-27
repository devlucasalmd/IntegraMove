package com.br.integramove.domain.treino.item;

import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.math.BigDecimal;


public class TreinoItem {

    private TreinoItemId id;
    private ExercicioId exercicioId;
    private TreinoId treinoId;
    private String nomeExercicio;
    private Integer series;
    private String repeticoes;
    private BigDecimal carga;
    private Integer descanso;
    private Integer ordem;

    public TreinoItem(
            TreinoItemId id,
            ExercicioId exercicioId,
            TreinoId treinoId,
            String nomeExercicio,
            Integer series,
            String repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
            this.id = id;
            this.exercicioId = exercicioId;
            this.treinoId = treinoId;
            this.nomeExercicio = nomeExercicio;
            this.series = series;
            this.repeticoes = repeticoes;
            this.carga = carga;
            this.descanso = descanso;
            this.ordem = ordem;
    }

    public TreinoItemId getId() {
        return id;
    }

    public ExercicioId getExercicioId() {
        return exercicioId;
    }

    public TreinoId getTreinoId() {
        return treinoId;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public BigDecimal getCarga() {
        return carga;
    }

    public Integer getDescanso() {
        return descanso;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setId(TreinoItemId id) {
        this.id = id;
    }

    public void setExercicioId(ExercicioId exercicioId) {
        this.exercicioId = exercicioId;
    }

    public void setTreinoId(TreinoId treinoId) {
        this.treinoId = treinoId;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public void setCarga(BigDecimal carga) {
        this.carga = carga;
    }

    public void setDescanso(Integer descanso) {
        this.descanso = descanso;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public void atualizar(
            Integer series,
            String repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.descanso = descanso;
        this.ordem = ordem;
    }
}
