package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.ContratoRequestDTO;
import com.br.integramove.api.dto.response.ContratoResponseDTO;
import com.br.integramove.application.contrato.inputs.CriarContratoInput;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {

    public static CriarContratoInput toInput(ContratoRequestDTO dto) {
        return new CriarContratoInput(
                dto.alunoId(),
                dto.planoId(),
                dto.dataInicio(),
                dto.diaVencimento(),
                dto.permiteRenovacaoAutomatica()
        );
    }

    public static ContratoResponseDTO toResponse(ContratoOutput output) {
        return new ContratoResponseDTO(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.dataInicio(),
                output.dataFim(),
                output.diaVencimento(),
                output.permiteRenovacaoAutomatica(),
                output.status(),
                output.documentoUrl(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}