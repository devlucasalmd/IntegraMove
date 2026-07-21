package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.TreinoRequestDTO;
import com.br.integramove.api.dto.response.TreinoResponseDTO;
import com.br.integramove.application.treino.treino.BuscarTreinoOutput;
import com.br.integramove.application.treino.treino.CriarTreinoInput;
import com.br.integramove.application.treino.treino.CriarTreinoOutput;
import com.br.integramove.application.treino.treino.ListarTreinosOutput;
import com.br.integramove.domain.enums.GrupoMuscular;
import org.springframework.stereotype.Component;

@Component
public class TreinoMapper {

    private static TreinoResponseDTO build(
            String id,
            String nome,
            String responsavel,
            String funcionalidade,
            String nivel,
            String repeticoes,
            String observacoes,
            GrupoMuscular grupoMuscular
    ){
        return new TreinoResponseDTO(
                id, nome, responsavel, funcionalidade, nivel, repeticoes, observacoes, grupoMuscular
        );
    }

    public static CriarTreinoInput toInput(TreinoRequestDTO dto){

        return new CriarTreinoInput(
                dto.nome(),
                dto.responsavel(),
                dto.funcionalidade(),
                dto.nivel(),
                dto.repeticoes(),
                dto.observacoes(),
                dto.grupoMuscular()
        );
    }

    public static TreinoResponseDTO toResponse(CriarTreinoOutput output){

        return build(
                output.id(),
                output.nome(),
                output.responsavel(),
                output.funcionalidade(),
                output.nivel(),
                output.repeticoes(),
                output.observacoes(),
                output.grupoMuscular()
        );
    }

    public static TreinoResponseDTO toResponse(BuscarTreinoOutput output){

        return build(
                output.id(),
                output.nome(),
                output.responsavel(),
                output.funcionalidade(),
                output.nivel(),
                output.repeticoes(),
                output.observacoes(),
                output.grupoMuscular()
        );
    }

    public static TreinoResponseDTO toResponse(ListarTreinosOutput output){

        return new TreinoResponseDTO(
                output.id(),
                output.nome(),
                output.responsavel(),
                output.funcionalidade(),
                output.nivel(),
                output.repeticoes(),
                output.observacoes(),
                output.grupoMuscular()
        );
    }
}
