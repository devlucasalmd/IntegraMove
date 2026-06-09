package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.TreinoAlunoRequestDTO;
import com.br.integramove.api.dto.response.TreinoAlunoResponseDTO;
import com.br.integramove.application.treino.aluno.outputs.BuscarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.inputs.CriarTreinoAlunoInput;
import com.br.integramove.application.treino.aluno.outputs.CriarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.outputs.ListarTreinoAlunoOutput;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TreinoAlunoMapper {

    private static TreinoAlunoResponseDTO build(
            String id,
            String alunoId,
            List<String> treinosIds,
            String nome,
            LocalDate dataInicio,
            LocalDate dataFim,
            Boolean ativo
    ) {
        return new TreinoAlunoResponseDTO(
                id,
                alunoId,
                treinosIds,
                nome,
                dataInicio,
                dataFim,
                ativo
        );
    }

    public static CriarTreinoAlunoInput toInput(
            String alunoId,
            TreinoAlunoRequestDTO dto
    ) {
        return new CriarTreinoAlunoInput(
                alunoId,
                dto.treinosIds(),
                dto.nome(),
                dto.dataInicio(),
                dto.dataFim(),
                dto.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(CriarTreinoAlunoOutput output) {
        return build(
                output.id(),
                output.alunoId(),
                output.treinosIds(),
                output.nome(),
                output.dataInicio(),
                output.dataFim(),
                output.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(BuscarTreinoAlunoOutput output) {
        return build(
                output.id(),
                output.alunoId(),
                output.treinosIds(),
                output.nome(),
                output.dataInicio(),
                output.dataFim(),
                output.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(ListarTreinoAlunoOutput output) {
        return build(
                output.id(),
                output.alunoId(),
                output.treinosIds(),
                output.nome(),
                output.dataInicio(),
                output.dataFim(),
                output.ativo()
        );
    }
}