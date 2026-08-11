package com.br.integramove.domain.contrato;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.StatusContrato;
import com.br.integramove.domain.plano.PlanoId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contrato {

    private ContratoId id;
    private AlunoId alunoId;
    private PlanoId planoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer diaVencimento;
    private Boolean permiteRenovacaoAutomatica;
    private StatusContrato status;
    private String documentoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Contrato(
            ContratoId id,
            AlunoId alunoId,
            PlanoId planoId,
            LocalDate dataInicio,
            LocalDate dataFim,
            Integer diaVencimento,
            Boolean permiteRenovacaoAutomatica,
            StatusContrato status,
            String documentoUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id do contrato é obrigatório");
        }
        if (alunoId == null) {
            throw new IllegalArgumentException("Aluno é obrigatório para o contrato");
        }
        if (planoId == null) {
            throw new IllegalArgumentException("Plano é obrigatório para o contrato");
        }
        if (dataInicio == null) {
            throw new IllegalArgumentException("Data de início é obrigatória");
        }
        if (dataFim == null || !dataFim.isAfter(dataInicio)) {
            throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
        }
        if (diaVencimento == null || diaVencimento < 1 || diaVencimento > 31) {
            throw new IllegalArgumentException("Dia de vencimento deve estar entre 1 e 31");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status do contrato é obrigatório");
        }

        this.id = id;
        this.alunoId = alunoId;
        this.planoId = planoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.diaVencimento = diaVencimento;
        this.permiteRenovacaoAutomatica = permiteRenovacaoAutomatica != null ? permiteRenovacaoAutomatica : false;
        this.status = status;
        this.documentoUrl = documentoUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Contrato criar(
            AlunoId alunoId,
            PlanoId planoId,
            LocalDate dataInicio,
            Integer duracaoDiasPlano,
            Integer diaVencimento,
            Boolean permiteRenovacaoAutomatica
    ) {
        if (duracaoDiasPlano == null) {
            throw new IllegalArgumentException("Plano não possui duração definida");
        }

        LocalDateTime agora = LocalDateTime.now();

        return new Contrato(
                ContratoId.novo(),
                alunoId,
                planoId,
                dataInicio,
                dataInicio.plusDays(duracaoDiasPlano),
                diaVencimento,
                permiteRenovacaoAutomatica,
                StatusContrato.ATIVO,
                null,
                agora,
                agora
        );
    }

    public ContratoId getId() {
        return id;
    }
    public AlunoId getAlunoId() {
        return alunoId;
    }
    public PlanoId getPlanoId() {
        return planoId;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public Integer getDiaVencimento() {
        return diaVencimento;
    }
    public Boolean getPermiteRenovacaoAutomatica() {
        return permiteRenovacaoAutomatica;
    }
    public StatusContrato getStatus() {
        return status;
    }
    public String getDocumentoUrl() {
        return documentoUrl;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void anexarDocumento(String documentoUrl) {
        if (documentoUrl == null || documentoUrl.isBlank()) {
            throw new IllegalArgumentException("Documento do contrato é obrigatório");
        }
        this.documentoUrl = documentoUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void encerrar() {
        this.status = StatusContrato.ENCERRADO;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean estaAtivo() {
        return this.status == StatusContrato.ATIVO;
    }
}