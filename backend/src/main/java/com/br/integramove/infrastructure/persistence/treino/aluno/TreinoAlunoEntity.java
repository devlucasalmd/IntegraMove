package com.br.integramove.infrastructure.persistence.treino.aluno;

import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    private String nome;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;


    @ManyToMany
    @JoinTable(
            name = "aluno_treino_treinos",
            joinColumns = @JoinColumn(name = "treino_aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private List<TreinoEntity> treinos = new ArrayList<>();

}
