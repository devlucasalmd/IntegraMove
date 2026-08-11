package com.br.integramove.application.contrato;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.enums.StatusContrato;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContratoRepository {

    Contrato salvar(Contrato contrato);

    Optional<Contrato> buscarPorId(ContratoId id);

    Optional<Contrato> buscarPorAlunoIdEStatus(AlunoId alunoId, StatusContrato status);

    List<Contrato> listarPorAlunoId(AlunoId alunoId);

    List<Contrato> listarVencidosSemRenovacaoAutomatica(StatusContrato status, LocalDate dataReferencia);
}