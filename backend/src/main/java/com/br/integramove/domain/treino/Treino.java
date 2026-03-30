package com.br.integramove.domain.treino;

import com.br.integramove.domain.aluno.AlunoId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Treino {
    private TreinoId id;
    private AlunoId alunoId;
//    private ProfessorId professorId;
    private String nome;
    private LocalDate dataInicio;
    private boolean ativo;

    private List<TreinoItem> itens = new ArrayList<>();
}
