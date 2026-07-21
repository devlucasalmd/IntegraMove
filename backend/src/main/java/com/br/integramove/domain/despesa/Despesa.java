package com.br.integramove.domain.despesa;

import com.br.integramove.domain.enums.CategoriaDespesa;
import com.br.integramove.domain.enums.StatusDespesa;
import com.br.integramove.domain.enums.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa {

    private DespesaId id;
    private String descricao;
    private CategoriaDespesa categoria;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private FormaPagamento formaPagamento;
    private StatusDespesa status;
    private String fornecedor;
    private String observacoes;

    public Despesa(
            DespesaId id,
            String descricao,
            CategoriaDespesa categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            FormaPagamento formaPagamento,
            StatusDespesa status,
            String fornecedor,
            String observacoes
    ) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.fornecedor = fornecedor;
        this.observacoes = observacoes;
    }

    public DespesaId getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaDespesa getCategoria() {
        return categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusDespesa getStatus() {
        return status;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public String getObservacoes() {
        return observacoes;
    }


    public static Despesa criar(
            String descricao,
            CategoriaDespesa categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            String fornecedor,
            String observacoes
    ) {
        return new Despesa(
                DespesaId.novo(),
                descricao,
                categoria,
                valor,
                dataVencimento,
                null,
                null,
                StatusDespesa.A_VENCER,
                fornecedor,
                observacoes
        );
    }

    public void atualizar(
            String descricao,
            CategoriaDespesa categoria,
            BigDecimal valor,
            LocalDate dataVencimento,
            String fornecedor,
            String observacoes
    ) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.fornecedor = fornecedor;
        this.observacoes = observacoes;
    }

    public void pagar(LocalDate dataPagamento, FormaPagamento formaPagamento) {
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.status = StatusDespesa.PAGA;
    }

    public void cancelar() {
        this.status = StatusDespesa.CANCELADA;
        this.dataPagamento = null;
        this.formaPagamento = null;
    }

}
