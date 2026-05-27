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
        if (id == null) {
            throw new IllegalArgumentException("Id do plano é obrigatório");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do plano é obrigatório");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do plano deve ser maior que zero");
        }

        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public PlanoId getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public Boolean getAtivo() {
        return ativo;
    }

    public void setId(PlanoId id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

    public boolean estaAtivo() {
        return Boolean.TRUE.equals(this.ativo);
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
