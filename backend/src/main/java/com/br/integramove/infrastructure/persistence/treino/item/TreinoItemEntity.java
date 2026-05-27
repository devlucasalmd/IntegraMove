package com.br.integramove.infrastructure.persistence.treino.item;

import com.br.integramove.infrastructure.persistence.treino.exercicio.ExercicioEntity;
import com.br.integramove.infrastructure.persistence.treino.treino.TreinoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treino_itens")
public class TreinoItemEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private TreinoEntity treino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private ExercicioEntity exercicio;

    private Integer series;
    private String repeticoes;
    private BigDecimal carga;
    private Integer descanso;
    private Integer ordem;
}
