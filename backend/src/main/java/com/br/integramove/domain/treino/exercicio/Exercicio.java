package com.br.integramove.domain.treino.exercicio;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;

public class Exercicio {

    private ExercicioId id;
    private String nome;
    private GrupoMuscular grupoMuscular;
    private String descricao;
    private Intensidade intensidade;
    private boolean ativo;


    public Exercicio(
            ExercicioId id,
            String nome,
            GrupoMuscular grupoMuscular,
            String descricao,
            Intensidade intensidade,
            Boolean ativo
    ){

        if(id == null) throw new IllegalArgumentException("Id obrigatorio");


        this.id = id;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.intensidade = intensidade;
        this.ativo = ativo;
    }

    public ExercicioId getId() {
        return id;
    }

    public void setId(ExercicioId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Intensidade getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Intensidade intensidade) {
        this.intensidade = intensidade;
    }

    public void atualizar(
            String nome,
            GrupoMuscular grupoMuscular,
            String descricao,
            Intensidade intensidade,
            Boolean ativo
    ) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.intensidade = intensidade;
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public boolean getAtivo(){
        return ativo;
    }

    public void desativar() {
        if (!this.ativo) {
            throw new IllegalStateException("Exercício já está inativo");
        }

        this.ativo = false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
