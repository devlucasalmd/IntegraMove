package com.br.integramove.infrastructure.persistence.treino.exercicio;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;
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
    @Enumerated(EnumType.STRING)
    private Intensidade intensidade;
    private Boolean ativo;
}
