package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.AlunoRequestDTO;
import com.br.integramove.api.dto.response.AlunoResponseDTO;
import com.br.integramove.application.aluno.AtualizarAlunoInput;
import com.br.integramove.application.aluno.BuscarAlunoOutput;
import com.br.integramove.application.aluno.CriarAlunoInput;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public static CriarAlunoInput toInput(AlunoRequestDTO dto){
        return new CriarAlunoInput(
                dto.nome(),
                dto.dataNascimento(),
                dto.cpf(),
                dto.genero(),
                dto.telefone(),
                dto.email(),
                dto.cep(),
                dto.estado(),
                dto.cidade(),
                dto.rua(),
                dto.numero(),
                dto.bairro(),
                dto.ativo()

        );
    }

    public static AtualizarAlunoInput toAtualizar(String id, AlunoRequestDTO dto) {
        return new AtualizarAlunoInput(
                id,
                dto.nome(),
                dto.dataNascimento(),
                dto.cpf(),
                dto.genero(),
                dto.telefone(),
                dto.email(),
                dto.cep(),
                dto.estado(),
                dto.cidade(),
                dto.rua(),
                dto.numero(),
                dto.bairro(),
                dto.ativo()
        );
    }

    public static AlunoResponseDTO toResponse(BuscarAlunoOutput output){
        return new AlunoResponseDTO(
                output.id(),
                output.nome(),
                output.cpf(),
                output.email(),
                output.ativo()
        );
    }
}
