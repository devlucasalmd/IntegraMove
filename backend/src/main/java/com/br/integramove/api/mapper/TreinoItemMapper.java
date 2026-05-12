package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.TreinoItemRequestDTO;
import com.br.integramove.api.dto.response.TreinoItemResponseDTO;
import com.br.integramove.application.treino.item.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TreinoItemMapper {

    private static TreinoItemResponseDTO build(
            String id,
            String exercicioId,
            Integer series,
            String repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
        return new TreinoItemResponseDTO(
                id, exercicioId, series, repeticoes, carga, descanso, ordem
        );
    }

    public static CriarTreinoItemInput toInput(String treinoId, TreinoItemRequestDTO dto){

        return new CriarTreinoItemInput(
                treinoId,
                dto.exercicioId(),
                dto.series(),
                dto.repeticoes(),
                dto.carga(),
                dto.descanso(),
                dto.ordem()
        );
    }

    public static AtualizarTreinoItemInput toAtualizar(String id, TreinoItemRequestDTO dto){

        return new AtualizarTreinoItemInput(
                id,
                dto.exercicioId(),
                dto.series(),
                dto.repeticoes(),
                dto.carga(),
                dto.descanso(),
                dto.ordem()
        );
    }

    public static TreinoItemResponseDTO toResponse(CriarTreinoItemOutput output){

        return build(
                output.id(),
                output.exercicioId(),
                output.series(),
                output.repeticoes(),
                output.carga(),
                output.descanso(),
                output.ordem()
        );
    }

    public static TreinoItemResponseDTO toResponse(BuscarTreinoItemOutput output){

        return build(
                output.id(),
                output.exercicioId(),
                output.series(),
                output.repeticoes(),
                output.carga(),
                output.descanso(),
                output.ordem()
        );
    }

    public static TreinoItemResponseDTO toResponse(AtualizarTreinoItemOutput output){

        return build(
                output.id(),
                output.exercicioId(),
                output.series(),
                output.repeticoes(),
                output.carga(),
                output.descanso(),
                output.ordem()
        );
    }

    public static TreinoItemResponseDTO toResponse(ListarTreinoItemOutput output){

        return build(
                output.id(),
                output.exercicioId(),
                output.series(),
                output.repeticoes(),
                output.carga(),
                output.descanso(),
                output.ordem()
        );
    }
}
