package com.br.integramove.domain.avaliacao;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final AvaliacaoId id;
    private LocalDate dataAvaliacao;
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal imc;
    private BigDecimal percentualGordura;
    private BigDecimal circuferencia;

    public Avaliacao(
            AvaliacaoId id,
            LocalDate dataAvaliacao,
            BigDecimal peso,
            BigDecimal altura,
            BigDecimal imc,
            BigDecimal percentualGordura,
            BigDecimal circuferencia
    ) {

        if(id == null) throw new IllegalArgumentException("Id obrigatorio");

        this.id = id;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.percentualGordura = percentualGordura;
        this.circuferencia = circuferencia;
    }

    public AvaliacaoId getId() {
        return id;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public BigDecimal getPercentualGordura() {
        return percentualGordura;
    }

    public BigDecimal getCircuferencia() {
        return circuferencia;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

