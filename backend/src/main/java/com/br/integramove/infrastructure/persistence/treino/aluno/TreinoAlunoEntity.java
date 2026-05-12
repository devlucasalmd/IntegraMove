package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.infrastructure.persistence.treino.item.TreinoItemEntity;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno_treino")
public class TreinoAlunoEntity {

    @Id
    private UUID id;

    @Column(name = "aluno_id", nullable = false)
    private UUID alunoId;
//
//    @Column(name = "professor_id", nullable = false)
//    private UUID professorId;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private TreinoEntity treino;

    private String nome;

    private LocalDate dataInicio;
    private boolean ativo;


}
