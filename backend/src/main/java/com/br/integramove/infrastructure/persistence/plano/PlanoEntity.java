package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.domain.enums.Periodicidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "planos")
public class PlanoEntity {

    @Id
    private UUID id;
    private String nome;
    private BigDecimal valor;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Periodicidade periodicidade;

    @Column(name = "duracao_dias")
    private Integer duracaoDias;

    private Boolean ativo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}