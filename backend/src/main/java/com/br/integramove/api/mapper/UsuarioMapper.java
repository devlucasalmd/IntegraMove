package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.UsuarioRequestDTO;
import com.br.integramove.api.dto.response.UsuarioResponseDTO;
import com.br.integramove.api.dto.response.UsuarioResumoResponseDTO;
import com.br.integramove.application.usuario.inputs.AtualizarUsuarioInput;
import com.br.integramove.application.usuario.inputs.CriarUsuarioInput;
import com.br.integramove.application.usuario.outputs.AtualizarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.BuscarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.CriarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.ListarUsuarioOutput;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static CriarUsuarioInput toInput(UsuarioRequestDTO dto) {

        return new CriarUsuarioInput(
                dto.nome(),
                dto.cpf(),
                dto.telefone(),
                dto.email(),
                dto.senha(),
                dto.perfil()
        );
    }

    public static AtualizarUsuarioInput toAtualizar(String id, UsuarioRequestDTO dto) {

        return new AtualizarUsuarioInput(
                id,
                dto.nome(),
                dto.telefone(),
                dto.email(),
                dto.perfil(),
                dto.ativo()
        );
    }

    public static UsuarioResumoResponseDTO toResumoResponse(ListarUsuarioOutput output) {

        return new UsuarioResumoResponseDTO(
                output.id(),
                output.nome(),
                output.perfil(),
                output.ativo()
        );
    }

    public static UsuarioResponseDTO toResponse(BuscarUsuarioOutput output) {

        return new UsuarioResponseDTO(
                output.id(),
                output.nome(),
                output.cpf(),
                output.telefone(),
                output.email(),
                output.perfil(),
                output.ativo()
        );
    }

    public static UsuarioResponseDTO toResponse(CriarUsuarioOutput output) {

        return new UsuarioResponseDTO(
                output.id(),
                output.nome(),
                output.cpf(),
                output.telefone(),
                output.email(),
                output.perfil(),
                output.ativo()
        );
    }

    public static UsuarioResponseDTO toResponse(AtualizarUsuarioOutput output) {

        return new UsuarioResponseDTO(
                output.id(),
                output.nome(),
                output.cpf(),
                output.telefone(),
                output.email(),
                output.perfil(),
                output.ativo()
        );
    }

}
