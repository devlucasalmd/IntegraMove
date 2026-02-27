package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.AvaliacaoRequestDTO;
import com.br.integramove.api.dto.response.AvaliacaoResponseDTO;
import com.br.integramove.application.avaliacao.BuscarAvaliacaoOutput;
import com.br.integramove.application.avaliacao.CriarAvaliacaoInput;

public class AvaliacaoMapper {

    public static CriarAvaliacaoInput toInput(AvaliacaoRequestDTO dto) {
        return new CriarAvaliacaoInput(
                dto.dataAvaliacao(),
                dto.peso(),
                dto.altura(),
                dto.imc(),
                dto.percentualGordura(),
                dto.circuferencia()
        );
    }

    public static AvaliacaoResponseDTO toResponse(BuscarAvaliacaoOutput output){
        return new AvaliacaoResponseDTO(
                output.id(),
                output.dataAvaliacao(),
                output.peso(),
                output.altura(),
                output.imc(),
                output.percentualGordura(),
                output.circuferencia()
        );
    }

}

