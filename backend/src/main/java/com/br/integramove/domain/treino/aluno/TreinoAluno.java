package com.br.integramove.domain.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.time.LocalDate;

public class TreinoAluno {
    private TreinoAlunoId id;
    private TreinoId treinoId;
    private AlunoId alunoId;
//    private ProfessorId professorId;
    private String nome;
    private LocalDate dataInicio;
    private boolean ativo;

    public TreinoAluno(
            TreinoAlunoId id,
            TreinoId treinoId,
            AlunoId alunoId,
            String nome,
            LocalDate dataInicio,
            boolean ativo
    ) {
        this.id = id;
        this.treinoId = treinoId;
        this.alunoId = alunoId;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.ativo = ativo;
    }

    public TreinoAlunoId getId() {
        return id;
    }

    public void setId(TreinoAlunoId id) {
        this.id = id;
    }

    public TreinoId getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(TreinoId treino) {
        this.treinoId = treinoId;
    }

    public AlunoId getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(AlunoId alunoId) {
        this.alunoId = alunoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
