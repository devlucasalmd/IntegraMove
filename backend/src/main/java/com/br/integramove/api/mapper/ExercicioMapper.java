package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.ExercicioRequestDTO;
import com.br.integramove.api.dto.response.ExercicioResponseDTO;
import com.br.integramove.application.treino.exercicio.*;
import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import com.br.integramove.domain.treino.exercicio.Intensidade;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper {

    public static CriarExercicioInput toInput(ExercicioRequestDTO dto) {

        return new CriarExercicioInput(
                dto.nome(),
                dto.grupoMuscular(),
                dto.descricao(),
                dto.intensidade(),
                dto.ativo()
        );
    }

    private static ExercicioResponseDTO build(
            String id,
            String nome,
            GrupoMuscular grupoMuscular,
            String descricao,
            Intensidade intensidade,
            boolean ativo
    ) {
        return new ExercicioResponseDTO(
                id, nome, grupoMuscular, descricao, intensidade, ativo
        );
    }

    public static AtualizarExercicioInput toAtualizar(String id, ExercicioRequestDTO dto) {

        return new AtualizarExercicioInput(
                id,
                dto.nome(),
                dto.grupoMuscular().toString(),
                dto.descricao(),
                dto.intensidade().toString(),
                dto.ativo()
        );
    }
    public static ExercicioResponseDTO toResponse(CriarExercicioOutput output) {
        return build(
                output.id(),
                output.nome(),
                output.grupoMuscular(),
                output.descricao(),
                output.intensidade(),
                output.ativo()
        );
    }

    public static ExercicioResponseDTO toResponse(BuscarExercicioOutput output) {
        return build(
                output.id(),
                output.nome(),
                output.grupoMuscular(),
                output.descricao(),
                output.intensidade(),
                output.ativo()
        );
    }

    public static ExercicioResponseDTO toResponse(ListarExercicioOutput output) {
        return build(
                output.id(),
                output.nome(),
                output.grupoMuscular(),
                null,
                output.intensidade(),
                output.ativo()
        );
    }

    public static ExercicioResponseDTO toResponse(AtualizarExercicioOutput output) {
        return build(
                output.id(),
                output.nome(),
                output.grupoMuscular(),
                output.descricao(),
                output.intensidade(),
                output.ativo()
        );
    }

}
