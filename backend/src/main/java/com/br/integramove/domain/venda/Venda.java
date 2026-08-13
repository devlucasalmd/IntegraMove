package com.br.integramove.domain.venda;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.StatusVenda;
import com.br.integramove.domain.enums.TipoVenda;
import com.br.integramove.domain.plano.PlanoId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Venda {

    private VendaId id;
    private AlunoId alunoId;
    private TipoVenda tipo;
    private PlanoId planoId;
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataVenda;
    private StatusVenda status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Venda(
            VendaId id,
            AlunoId alunoId,
            TipoVenda tipo,
            PlanoId planoId,
            String descricao,
            BigDecimal valor,
            LocalDate dataVenda,
            StatusVenda status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id da venda é obrigatório");
        }
        if (alunoId == null) {
            throw new IllegalArgumentException("Aluno é obrigatório para a venda");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo da venda é obrigatório");
        }
        if (tipo == TipoVenda.PLANO && planoId == null) {
            throw new IllegalArgumentException("Plano é obrigatório para venda do tipo PLANO");
        }
        if (tipo != TipoVenda.PLANO) {
            if (planoId != null) {
                throw new IllegalArgumentException("Plano só pode ser informado em venda do tipo PLANO");
            }
            if (descricao == null || descricao.isBlank()) {
                throw new IllegalArgumentException("Descrição é obrigatória para venda que não seja do tipo PLANO");
            }
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da venda deve ser maior que zero");
        }
        if (dataVenda == null) {
            throw new IllegalArgumentException("Data da venda é obrigatória");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status da venda é obrigatório");
        }

        this.id = id;
        this.alunoId = alunoId;
        this.tipo = tipo;
        this.planoId = planoId;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVenda = dataVenda;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Venda criar(
            AlunoId alunoId,
            TipoVenda tipo,
            PlanoId planoId,
            String descricao,
            BigDecimal valor,
            LocalDate dataVenda
    ) {
        LocalDateTime agora = LocalDateTime.now();

        return new Venda(
                VendaId.novo(),
                alunoId,
                tipo,
                planoId,
                descricao,
                valor,
                dataVenda,
                StatusVenda.PENDENTE,
                agora,
                agora
        );
    }

    public VendaId getId() {
        return id;
    }
    public AlunoId getAlunoId() {
        return alunoId;
    }
    public TipoVenda getTipo() {
        return tipo;
    }
    public PlanoId getPlanoId() {
        return planoId;
    }
    public String getDescricao() {
        return descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public StatusVenda getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean ehPlano() {
        return this.tipo == TipoVenda.PLANO;
    }

    public void concluir() {
        this.status = StatusVenda.CONCLUIDA;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancelar() {
        this.status = StatusVenda.CANCELADA;
        this.updatedAt = LocalDateTime.now();
    }
}