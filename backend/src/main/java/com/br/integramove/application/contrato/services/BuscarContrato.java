package com.br.integramove.application.contrato.services;

import com.br.integramove.api.exception.contrato.ContratoNaoEncontradoException;
import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.contrato.ContratoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarContrato {

    private final ContratoRepository contratoRepository;

    public BuscarContrato(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public ContratoOutput buscar(String id) {

        ContratoId contratoId = ContratoId.from(id);

        Contrato contrato = contratoRepository.buscarPorId(contratoId)
                .orElseThrow(() -> new ContratoNaoEncontradoException(contratoId));

        return new ContratoOutput(
                contrato.getId().getValue().toString(),
                contrato.getAlunoId().getValue().toString(),
                contrato.getVendaId() != null ? contrato.getVendaId().getValue().toString() : null,
                contrato.getPlanoId().getValue().toString(),
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getDiaVencimento(),
                contrato.getPermiteRenovacaoAutomatica(),
                contrato.getStatus(),
                contrato.getDocumentoUrl(),
                contrato.getCreatedAt(),
                contrato.getUpdatedAt()
        );
    }
}