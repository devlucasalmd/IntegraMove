package com.br.integramove.infrastructure.persistence.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercicios")
public class ExercicioEntity {

    @Id
    private UUID id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private GrupoMuscular grupoMuscular;
    private String descricao;
    private String intensidade;
    private Boolean ativo;
}
