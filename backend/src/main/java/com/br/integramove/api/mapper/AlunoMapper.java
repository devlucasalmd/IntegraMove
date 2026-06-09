package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.AlunoRequestDTO;
import com.br.integramove.api.dto.request.EnderecoRequestDTO;
import com.br.integramove.api.dto.response.AlunoResponseDTO;
import com.br.integramove.api.dto.response.AlunoResumoResponseDTO;
import com.br.integramove.api.dto.response.EnderecoResponseDTO;
import com.br.integramove.application.aluno.inputs.AtualizarAlunoInput;
import com.br.integramove.application.aluno.inputs.CriarAlunoInput;
import com.br.integramove.application.aluno.inputs.EnderecoInput;
import com.br.integramove.application.aluno.outputs.*;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public static CriarAlunoInput toInput(AlunoRequestDTO dto){

        EnderecoRequestDTO enderecoDTO = dto.enderecoDTO();

        return new CriarAlunoInput(
                dto.nome(),
                dto.dataNascimento(),
                dto.cpf(),
                dto.genero(),
                dto.telefone(),
                dto.email(),
                dto.status(),
                dto.planoId(),
                toEnderecoInput(dto.enderecoDTO())
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
                dto.status(),
                toEnderecoInput(dto.enderecoDTO())
        );
    }

    public static AlunoResumoResponseDTO toResumoResponse(ListarAlunosOutput output){
        return new AlunoResumoResponseDTO(
                output.id(),
                output.nome(),
                output.planoId(),
                output.nomePlano(),
                output.pagamento(),
                output.status()
        );
    }
    public static AlunoResponseDTO toResponse(BuscarAlunoOutput output){
        return new AlunoResponseDTO(
                output.id(),
                output.nome(),
                output.dataNascimento(),
                output.cpf(),
                output.genero(),
                output.telefone(),
                output.email(),
                output.status(),
                toEnderecoResponse(output.endereco()),
                output.planoId(),
                output.nomePlano()
        );
    }

    public static AlunoResponseDTO toResponse(CriarAlunoOutput output) {
        return new AlunoResponseDTO(
                output.id(),
                output.nome(),
                output.dataNascimento(),
                output.cpf(),
                output.genero(),
                output.telefone(),
                output.email(),
                output.status(),
                toEnderecoResponse(output.endereco()),
                null,
                null
        );
    }

    public static AlunoResponseDTO toResponse(AtualizarAlunoOutput output) {
        return new AlunoResponseDTO(
                output.id(),
                output.nome(),
                output.dataNascimento(),
                output.cpf(),
                output.genero(),
                output.telefone(),
                output.email(),
                output.status(),
                toEnderecoResponse(output.endereco()),
                null,
                null
        );
    }

    private static EnderecoInput toEnderecoInput(EnderecoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return new EnderecoInput(
                dto.cep(),
                dto.estado(),
                dto.cidade(),
                dto.rua(),
                dto.numero(),
                dto.bairro()
        );
    }

    private static EnderecoResponseDTO toEnderecoResponse(EnderecoOutput output) {
        if (output == null) {
            return null;
        }

        return new EnderecoResponseDTO(
                output.cep(),
                output.estado(),
                output.cidade(),
                output.rua(),
                output.numero(),
                output.bairro()
        );
    }

}
