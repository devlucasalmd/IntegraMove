package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.TreinoItemRequestDTO;
import com.br.integramove.api.dto.response.TreinoItemResponseDTO;
import com.br.integramove.application.treino.item.inputs.AtualizarTreinoItemInput;
import com.br.integramove.application.treino.item.inputs.CriarTreinoItemInput;
import com.br.integramove.application.treino.item.outputs.AtualizarTreinoItemOutput;
import com.br.integramove.application.treino.item.outputs.BuscarTreinoItemOutput;
import com.br.integramove.application.treino.item.outputs.CriarTreinoItemOutput;
import com.br.integramove.application.treino.item.outputs.ListarTreinoItemOutput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TreinoItemMapper {

    private static TreinoItemResponseDTO build(
            String id,
            String exercicioId,
            String nomeExercicio,
            Integer series,
            String repeticoes,
            BigDecimal carga,
            Integer descanso,
            Integer ordem
    ) {
        return new TreinoItemResponseDTO(
                id, exercicioId, nomeExercicio, series, repeticoes, carga, descanso, ordem
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
                output.nomeExercicio(),
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
                output.nomeExercicio(),
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
                output.nomeExercicio(),
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
                output.nomeExercicio(),
                output.series(),
                output.repeticoes(),
                output.carga(),
                output.descanso(),
                output.ordem()
        );
    }
}
