package com.br.integramove.domain.financas.contasReceber;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.CategoriaContaReceber;
import com.br.integramove.domain.enums.StatusContaReceber;
import com.br.integramove.domain.enums.FormaPagamento;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.financas.contasReceber.ContaReceberId;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaReceber {
    private ContaReceberId id;
    private String descricao;
    private CategoriaContaReceber categoria;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataRecebimento;
    private FormaPagamento formaPagamento;
    private StatusContaReceber status;
    private AlunoId alunoId;
    private PlanoId planoId;
    private String observacoes;

    public ContaReceber(
            ContaReceberId id,
            String descricao,
            CategoriaContaReceber categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            LocalDate dataRecebimento,
            FormaPagamento formaPagamento,
            StatusContaReceber status,
            AlunoId alunoId,
            PlanoId planoId,
            String observacoes
    ) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataRecebimento = dataRecebimento;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.alunoId = alunoId;
        this.planoId = planoId;
        this.observacoes = observacoes;
    }

    public ContaReceberId getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaContaReceber getCategoria() {
        return categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusContaReceber getStatus() {
        return status;
    }

    public AlunoId getAlunoId() {
        return alunoId;
    }

    public PlanoId getPlanoId() {
        return planoId;
    }

    public String getObservacoes() {
        return observacoes;
    }


    public static ContaReceber criar(
            String descricao,
            CategoriaContaReceber categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            AlunoId alunoId,
            PlanoId planoId,
            String observacoes
    ) {
        return new ContaReceber(
                ContaReceberId.novo(),
                descricao,
                categoria,
                valor,
                dataVencimento,
                null,
                null,
                StatusContaReceber.A_VENCER,
                alunoId,
                planoId,
                observacoes
        );
    }

    public void atualizar(
            String descricao,
            CategoriaContaReceber categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            AlunoId alunoId,
            PlanoId planoId,
            String observacoes
    ) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.alunoId = alunoId;
        this.planoId = planoId;
        this.observacoes = observacoes;
    }

    public void receber(LocalDate dataRecebimento, FormaPagamento formaPagamento) {
        this.dataRecebimento = dataRecebimento;
        this.formaPagamento = formaPagamento;
        this.status = StatusContaReceber.RECEBIDA;
    }

    public void cancelar() {
        this.status = StatusContaReceber.CANCELADA;
        this.dataRecebimento = null;
        this.formaPagamento = null;
    }
}
