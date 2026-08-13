package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.VendaRequestDTO;
import com.br.integramove.api.dto.response.VendaResponseDTO;
import com.br.integramove.application.venda.inputs.CriarVendaInput;
import com.br.integramove.application.venda.outputs.VendaOutput;
import org.springframework.stereotype.Component;

@Component
public class VendaMapper {

    public static CriarVendaInput toInput(VendaRequestDTO dto) {
        return new CriarVendaInput(
                dto.alunoId(),
                dto.tipo(),
                dto.planoId(),
                dto.descricao(),
                dto.valor(),
                dto.dataVenda(),
                dto.diaVencimento(),
                dto.permiteRenovacaoAutomatica()
        );
    }

    public static VendaResponseDTO toResponse(VendaOutput output) {
        return new VendaResponseDTO(
                output.id(),
                output.alunoId(),
                output.tipo(),
                output.planoId(),
                output.descricao(),
                output.valor(),
                output.periodicidade(),
                output.dataVenda(),
                output.status(),
                output.contratoId(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}