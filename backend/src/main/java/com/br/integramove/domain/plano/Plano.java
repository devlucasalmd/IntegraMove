package com.br.integramove.domain.plano;

import java.math.BigDecimal;

public class Plano {

    private PlanoId id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Boolean ativo;


    public Plano(
            PlanoId id,
            String nome,
            BigDecimal valor,
            String descricao,
            Boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public PlanoId getId() {
        return id;
    }

    public void setId(PlanoId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void atualizarDados(
            String nome,
            BigDecimal valor,
            String descricao,
            Boolean ativo
    ){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.ativo = ativo;
    }
}
