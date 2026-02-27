package com.br.integramove.domain.avaliacao;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final AvalicaoId id;
    private LocalDate dataAvaliacao;
    private Double peso;
    private Double altura;
    private Double imc;
    private Double percentualGordura;
    private Double circuferencia;

    public Avaliacao(
            AvalicaoId id,
            LocalDate dataAvaliacao,
            Double peso,
            Double altura,
            Double imc,
            Double percentualGordura,
            Double circuferencia
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

    public AvalicaoId getId() {
        return id;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getImc() {
        return imc;
    }

    public Double getPercentualGordura() {
        return percentualGordura;
    }

    public Double getCircuferencia() {
        return circuferencia;
    }

}

