package com.br.integramove.infrastructure.persistence.treino.item;

import com.br.integramove.infrastructure.persistence.treino.TreinoEntity;
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
@Table(name = "treinoItem")
public class TreinoItemEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private TreinoEntity treino;

    @Column(name = "exercicio_id", nullable = false)
    private UUID exercicioId;

    private Integer series;
    private Integer repeticoes;
    private BigDecimal carga;
    private Integer descanso;
    private Integer ordem;

}
