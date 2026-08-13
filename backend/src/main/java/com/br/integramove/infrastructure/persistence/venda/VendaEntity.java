package com.br.integramove.infrastructure.persistence.venda;

import com.br.integramove.domain.enums.StatusVenda;
import com.br.integramove.domain.enums.TipoVenda;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendas")
public class VendaEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @Enumerated(EnumType.STRING)
    private TipoVenda tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;

    private String descricao;

    private BigDecimal valor;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @Enumerated(EnumType.STRING)
    private StatusVenda status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}