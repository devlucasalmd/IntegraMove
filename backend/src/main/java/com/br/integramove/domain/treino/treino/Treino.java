package com.br.integramove.domain.treino.treino;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import com.br.integramove.domain.treino.item.TreinoItem;

import java.util.ArrayList;
import java.util.List;

public class Treino {

    private TreinoId id;
    private String nome;
    private String responsavel;
    private String funcionalidade;
    private String nivel;
    private String repeticoes;
    private String observacoes;
    private GrupoMuscular grupoMuscular;
    private List<TreinoItem> exercicios = new ArrayList<>();


    public Treino(
            TreinoId id,
            String nome,
            String responsavel,
            String funcionalidade,
            String nivel,
            String repeticoes,
            String observacoes,
            GrupoMuscular grupoMuscular
    ) {
        this.id = id;
        this.nome = nome;
        this.responsavel = responsavel;
        this.funcionalidade = funcionalidade;
        this.nivel = nivel;
        this.repeticoes = repeticoes;
        this.observacoes = observacoes;
        this.grupoMuscular = grupoMuscular;
        this.exercicios = new ArrayList<>();
    }

    public void adicionarExercicios(List<TreinoItem> itens) {
        this.exercicios.addAll(itens);
    }

    public TreinoId getId() {
        return id;
    }

    public void setId(TreinoId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public String getObservacoes() { return observacoes; }

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public GrupoMuscular getGrupoMuscular() { return grupoMuscular; }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) { this.grupoMuscular = grupoMuscular; }

    public List<TreinoItem> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<TreinoItem> itens) {
        this.exercicios = itens;
    }

}
