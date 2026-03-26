package com.br.integramove.infrastructure.persistence.treino;

import com.br.integramove.domain.treino.ficha.Ficha;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treinos")
public class TreinoEntity {

    @Id
    private UUID id;

    @Column(name = "aluno_id", nullable = false)
    private UUID alunoId;

    @Column(name = "professor_id", nullable = false)
    private UUID professorId;

    private String nome;
    private LocalDate dataInicio;
    private boolean ativo;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichaEntity> exercicios = new ArrayList<>();
}
