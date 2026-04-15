package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.TreinoAlunoRequestDTO;
import com.br.integramove.api.dto.response.TreinoAlunoResponseDTO;
import com.br.integramove.application.treino.aluno.BuscarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.CriarTreinoAlunoInput;
import com.br.integramove.application.treino.aluno.CriarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.ListarTreinoAlunoOutput;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TreinoAlunoMapper {

    private static TreinoAlunoResponseDTO build(
            String id,
            String treinoId,
            String alunoId,
//      ProfessorId professorId;
            String nome,
            LocalDate dataInicio,
            Boolean ativo
    ){
        return new TreinoAlunoResponseDTO(
                id, treinoId, alunoId, nome, dataInicio, ativo
        );
    }

    public static CriarTreinoAlunoInput toInput(TreinoAlunoRequestDTO dto){

        return new CriarTreinoAlunoInput(
                dto.id(),
                dto.treinoId(),
                dto.alunoId(),
                dto.nome(),
                dto.dataInicio(),
                dto.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(CriarTreinoAlunoOutput output){

        return build(
                output.id(),
                output.treinoId(),
                output.alunoId(),
                output.nome(),
                output.dataInicio(),
                output.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(BuscarTreinoAlunoOutput output){

        return build(
                output.id(),
                output.treinoId(),
                output.alunoId(),
                output.nome(),
                output.dataInicio(),
                output.ativo()
        );
    }

    public static TreinoAlunoResponseDTO toResponse(ListarTreinoAlunoOutput output){

        return build(
                output.id(),
                output.treinoId(),
                output.alunoId(),
                output.nome(),
                output.dataInicio(),
                output.ativo()
        );
    }
}
