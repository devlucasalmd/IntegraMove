package com.br.integramove.domain.avaliacao;

import com.br.integramove.domain.aluno.AlunoId;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Avaliacao {

    private final AvaliacaoId id;
    private AlunoId alunoId;
    private LocalDate dataAvaliacao;
    private BigDecimal remadaBracoD;
    private BigDecimal remadaBracoE;
    private BigDecimal elevacaoLatD;
    private BigDecimal elevacaoLatE;
    private BigDecimal extensaoJoelhoD;
    private BigDecimal extensaoJoelhoE;
    private BigDecimal flexaoJoelhoD;
    private BigDecimal flexaoJoelhoE;
    private BigDecimal extensaoQuadrilD;
    private BigDecimal extensaoQuadrilE;



    public Avaliacao(
            AvaliacaoId id,
            AlunoId alunoId,
            LocalDate dataAvaliacao,
            BigDecimal remadaBracoD,
            BigDecimal remadaBracoE,
            BigDecimal elevacaoLatD,
            BigDecimal elevacaoLatE,
            BigDecimal extensaoJoelhoD,
            BigDecimal extensaoJoelhoE,
            BigDecimal flexaoJoelhoD,
            BigDecimal flexaoJoelhoE,
            BigDecimal extensaoQuadrilD,
            BigDecimal extensaoQuadrilE
            ) {

        if(id == null) throw new IllegalArgumentException("Id obrigatorio");

        this.id = id;
        this.alunoId = alunoId;
        this.dataAvaliacao = dataAvaliacao;
        this.remadaBracoD = remadaBracoD;
        this.remadaBracoE = remadaBracoE;
        this.elevacaoLatD = elevacaoLatD;
        this.elevacaoLatE = elevacaoLatE;
        this.extensaoJoelhoD = extensaoJoelhoD;
        this.extensaoJoelhoE = extensaoJoelhoE;
        this.flexaoJoelhoD = flexaoJoelhoD;
        this.flexaoJoelhoE = flexaoJoelhoE;
        this.extensaoQuadrilD = extensaoQuadrilD;
        this.extensaoQuadrilE = extensaoQuadrilE;
    }

    public AvaliacaoId getId() {
        return id;
    }

    public AlunoId getAlunoId() { return alunoId; }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public BigDecimal getRemadaBracoD() {
        return remadaBracoD;
    }

    public BigDecimal getRemadaBracoE() {
        return remadaBracoE;
    }

    public BigDecimal getElevacaoLatD() {
        return elevacaoLatD;
    }

    public BigDecimal getElevacaoLatE() {
        return elevacaoLatE;
    }

    public BigDecimal getExtensaoJoelhoD() {
        return extensaoJoelhoD;
    }

    public BigDecimal getExtensaoJoelhoE() {
        return extensaoJoelhoE;
    }

    public BigDecimal getFlexaoJoelhoD() {
        return flexaoJoelhoD;
    }

    public BigDecimal getFlexaoJoelhoE() {
        return flexaoJoelhoE;
    }

    public BigDecimal getExtensaoQuadrilD() {
        return extensaoQuadrilD;
    }

    public BigDecimal getExtensaoQuadrilE() {
        return extensaoQuadrilE;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

