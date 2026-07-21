package com.br.integramove.infrastructure.persistence.despesa;

import com.br.integramove.domain.enums.CategoriaDespesa;
import com.br.integramove.domain.enums.StatusDespesa;
import com.br.integramove.domain.enums.FormaPagamento;
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
@Table(name = "despesas")
public class DespesaEntity {

    @Id
    private UUID id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoria;

    private BigDecimal valor;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusDespesa status;

    private String fornecedor;

    private String observacoes;
}
