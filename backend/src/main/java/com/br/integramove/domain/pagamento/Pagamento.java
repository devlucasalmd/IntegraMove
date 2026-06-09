package com.br.integramove.domain.pagamento;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.plano.PlanoId;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pagamento {

    private PagamentoId id;
    private AlunoId alunoId;
    private PlanoId planoId;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private FormaPagamento formaPagamento;
    private StatusPagamento status;
    private String observacoes;

    public Pagamento(
            PagamentoId id,
            AlunoId alunoId,
            PlanoId planoId,
            BigDecimal valor,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            FormaPagamento formaPagamento,
            StatusPagamento status,
            String observacoes
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id do pagamento é obrigatório");
        }

        if (alunoId == null) {
            throw new IllegalArgumentException("Aluno é obrigatório para o pagamento");
        }

        if (planoId == null) {
            throw new IllegalArgumentException("Plano é obrigatório para o pagamento");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do pagamento deve ser maior que zero");
        }

        if (dataVencimento == null) {
            throw new IllegalArgumentException("Data de vencimento é obrigatória");
        }

        this.id = id;
        this.alunoId = alunoId;
        this.planoId = planoId;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.observacoes = observacoes;
    }

    public PagamentoId getId() {
        return id;
    }

    public AlunoId getAlunoId() {
        return alunoId;
    }

    public PlanoId getPlanoId() {
        return planoId;
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

    public StatusPagamento getStatus() {
        return status;
    }

    public String getObservacoes() { return observacoes; }

    public static Pagamento criar(
            AlunoId alunoId,
            PlanoId planoId,
            BigDecimal valor,
            LocalDate dataVencimento,
            String observacoes
    ) {
        return new Pagamento(
                PagamentoId.novo(),
                alunoId,
                planoId,
                valor,
                dataVencimento,
                null,
                null,
                StatusPagamento.A_VENCER,
                observacoes
        );
    }

    public void pagar(FormaPagamento formaPagamento, LocalDate dataPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento é obrigatória");
        }

        this.formaPagamento = formaPagamento;
        this.dataPagamento = dataPagamento != null ? dataPagamento : LocalDate.now();
        this.status = StatusPagamento.PAGO;
    }

    public void cancelar() {
        this.status = StatusPagamento.CANCELADO;
    }

    public void marcarComoPago(FormaPagamento formaPagamento, LocalDate dataPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento é obrigatória");
        }

        this.formaPagamento = formaPagamento;
        this.dataPagamento = dataPagamento != null ? dataPagamento : LocalDate.now();
        this.status = StatusPagamento.PAGO;
    }

    public void marcarComoVencido() {
        if (this.status != StatusPagamento.PAGO &&
                LocalDate.now().isAfter(this.dataVencimento)) {
            this.status = StatusPagamento.VENCIDO;
        }
    }
}
