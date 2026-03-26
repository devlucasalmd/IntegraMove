package com.br.integramove.infrastructure.persistence.treino;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "fichas")
public class FichaEntity {

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
