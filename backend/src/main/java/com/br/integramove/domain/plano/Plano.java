package com.br.integramove.domain.plano;

import com.br.integramove.api.exception.plano.ValorPlanoImutavelException;
import com.br.integramove.domain.enums.Periodicidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Plano {

    private PlanoId id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Periodicidade periodicidade;
    private Integer duracaoDias;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Plano(
            PlanoId id,
            String nome,
            BigDecimal valor,
            String descricao,
            Periodicidade periodicidade,
            Integer duracaoDias,
            Boolean ativo,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
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
        this.periodicidade = periodicidade;
        this.duracaoDias = duracaoDias;
        this.ativo = ativo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }
    public Integer getDuracaoDias() {
        return duracaoDias;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
        this.updatedAt = LocalDateTime.now();
    }

    public void inativar() {
        this.ativo = false;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean estaAtivo() {
        return Boolean.TRUE.equals(this.ativo);
    }

    public void atualizarDados(
            String nome,
            BigDecimal valor,
            String descricao,
            Periodicidade periodicidade,
            Integer duracaoDias,
            Boolean ativo
    ){
        if (valor != null && this.valor.compareTo(valor) != 0) {
            throw new ValorPlanoImutavelException();
        }

        this.nome = nome;
        this.descricao = descricao;
        this.periodicidade = periodicidade;
        this.duracaoDias = duracaoDias;
        this.ativo = ativo != null ? ativo : this.ativo;
        this.updatedAt = LocalDateTime.now();
    }
}