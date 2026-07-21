package com.br.integramove.infrastructure.persistence.contaReceber;

import com.br.integramove.domain.enums.CategoriaContaReceber;
import com.br.integramove.domain.enums.StatusContaReceber;
import com.br.integramove.domain.enums.FormaPagamento;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contas_receber")
public class ContaReceberEntity {

    @Id
    private UUID id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaContaReceber categoria;

    private BigDecimal valor;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusContaReceber status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;

    private String observacoes;
}
