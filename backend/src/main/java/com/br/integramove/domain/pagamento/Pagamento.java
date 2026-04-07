package com.br.integramove.domain.pagamento;

import com.br.integramove.infrastructure.persistence.pagamento.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Pagamento {

    private PagamentoId id;
    private FormaPagamento formaPagamento;
    private BigDecimal valor;
    private LocalDate data;
    private String status;

    public Pagamento(PagamentoId id, FormaPagamento formaPagamento, BigDecimal valor, LocalDate data, String status) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }


    public PagamentoId getId() {
        return id;
    }

    public void setId(PagamentoId id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
