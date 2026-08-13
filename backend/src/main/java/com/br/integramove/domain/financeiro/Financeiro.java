package com.br.integramove.domain.financeiro;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.enums.FormaPagamento;
import com.br.integramove.domain.enums.StatusPagamento;
import com.br.integramove.domain.venda.VendaId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Financeiro {

    private FinanceiroId id;
    private AlunoId alunoId;
    private VendaId vendaId;
    private ContratoId contratoId;
    private BigDecimal valor;
    private Integer numeroParcela;
    private Integer totalParcelas;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private StatusPagamento status;
    private FormaPagamento formaPagamento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Financeiro(
            FinanceiroId id,
            AlunoId alunoId,
            VendaId vendaId,
            ContratoId contratoId,
            BigDecimal valor,
            Integer numeroParcela,
            Integer totalParcelas,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            StatusPagamento status,
            FormaPagamento formaPagamento,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id da cobrança é obrigatório");
        }
        if (alunoId == null) {
            throw new IllegalArgumentException("Aluno é obrigatório para a cobrança");
        }
        if (vendaId == null) {
            throw new IllegalArgumentException("Venda é obrigatória para a cobrança");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da cobrança deve ser maior que zero");
        }
        if (dataVencimento == null) {
            throw new IllegalArgumentException("Data de vencimento é obrigatória");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status da cobrança é obrigatório");
        }

        this.id = id;
        this.alunoId = alunoId;
        this.vendaId = vendaId;
        this.contratoId = contratoId;
        this.valor = valor;
        this.numeroParcela = numeroParcela;
        this.totalParcelas = totalParcelas;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Financeiro criar(
            AlunoId alunoId,
            VendaId vendaId,
            ContratoId contratoId,
            BigDecimal valor,
            Integer numeroParcela,
            Integer totalParcelas,
            LocalDate dataVencimento
    ) {
        LocalDateTime agora = LocalDateTime.now();

        return new Financeiro(
                FinanceiroId.novo(),
                alunoId,
                vendaId,
                contratoId,
                valor,
                numeroParcela,
                totalParcelas,
                dataVencimento,
                null,
                StatusPagamento.PENDENTE,
                null,
                agora,
                agora
        );
    }

    public FinanceiroId getId() {
        return id;
    }
    public AlunoId getAlunoId() {
        return alunoId;
    }
    public VendaId getVendaId() {
        return vendaId;
    }
    public ContratoId getContratoId() {
        return contratoId;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public Integer getNumeroParcela() {
        return numeroParcela;
    }
    public Integer getTotalParcelas() {
        return totalParcelas;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public StatusPagamento getStatus() {
        return status;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Status ATRASADO não é persistido (sem job de vencimento ainda) — é calculado
     * sob demanda na leitura: PENDENTE cuja dataVencimento já passou é exibido como
     * ATRASADO, sem mudar o status armazenado.
     */
    public StatusPagamento statusCalculado() {
        if (this.status == StatusPagamento.PENDENTE && this.dataVencimento.isBefore(LocalDate.now())) {
            return StatusPagamento.ATRASADO;
        }
        return this.status;
    }

    public void registrarPagamento(FormaPagamento formaPagamento, LocalDate dataPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento é obrigatória");
        }

        this.formaPagamento = formaPagamento;
        this.dataPagamento = dataPagamento != null ? dataPagamento : LocalDate.now();
        this.status = StatusPagamento.PAGO;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancelar() {
        this.status = StatusPagamento.CANCELADO;
        this.updatedAt = LocalDateTime.now();
    }
}