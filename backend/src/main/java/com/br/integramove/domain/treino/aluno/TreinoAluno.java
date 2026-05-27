package com.br.integramove.domain.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.time.LocalDate;

public class TreinoAluno {
    private TreinoAlunoId id;
    private AlunoId alunoId;
    private TreinoId treinoId;
//    private ProfessorId professorId;
    private String nome;
    private LocalDate dataInicio;
    private boolean ativo;

    public TreinoAluno(
            TreinoAlunoId id,
            AlunoId alunoId,
            TreinoId treinoId,
            String nome,
            LocalDate dataInicio,
            boolean ativo
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id da ficha de treino é obrigatório");
        }

        if (alunoId == null) {
            throw new IllegalArgumentException("Aluno é obrigatório");
        }

        if (treinoId == null) {
            throw new IllegalArgumentException("Treino é obrigatório");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da ficha é obrigatório");
        }

        if (dataInicio == null) {
            throw new IllegalArgumentException("Data de início é obrigatória");
        }

        this.id = id;
        this.alunoId = alunoId;
        this.treinoId = treinoId;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.ativo = ativo;
    }

    public TreinoAlunoId getId() {
        return id;
    }
    public AlunoId getAlunoId() {
        return alunoId;
    }
    public TreinoId getTreinoId() {
        return treinoId;
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getDataInicio() { return dataInicio; }

    public void setId(TreinoAlunoId id) {
        this.id = id;
    }
    public void setAlunoId(AlunoId alunoId) {
        this.alunoId = alunoId;
    }
    public void setTreinoId(TreinoId treinoId) {
        this.treinoId = treinoId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }
}
