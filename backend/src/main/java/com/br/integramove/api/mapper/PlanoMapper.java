package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.PlanoRequestDTO;
import com.br.integramove.api.dto.response.PlanoResponseDTO;
import com.br.integramove.application.plano.inputs.AtualizarPlanoInput;
import com.br.integramove.application.plano.inputs.CriarPlanoInput;
import com.br.integramove.application.plano.outputs.AtualizarPlanoOutput;
import com.br.integramove.application.plano.outputs.BuscarPlanoOutput;
import com.br.integramove.application.plano.outputs.CriarPlanoOutput;
import com.br.integramove.application.plano.outputs.ListarPlanosOutput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PlanoMapper {

    public static PlanoResponseDTO build(
            String id,
            String nome,
            BigDecimal valor,
            String descricao,
            Boolean ativo
    ){
        return new PlanoResponseDTO(
                id, nome, valor, descricao, ativo
        );
    }


    public static CriarPlanoInput toInput(PlanoRequestDTO dto){

        return new CriarPlanoInput(
                dto.nome(),
                dto.valor(),
                dto.descricao(),
                dto.ativo()
        );
    }

    public static AtualizarPlanoInput toAtualizar(String id, PlanoRequestDTO dto){

        return new AtualizarPlanoInput(
                id,
                dto.nome(),
                dto.valor(),
                dto.descricao(),
                dto.ativo()
        );
    }

    public static PlanoResponseDTO toResponse(CriarPlanoOutput output){

        return build(
                output.id(),
                output.nome(),
                output.valor(),
                output.descricao(),
                output.ativo()
        );
    }

    public static PlanoResponseDTO toResponse(BuscarPlanoOutput output){

        return build(
                output.id(),
                output.nome(),
                output.valor(),
                output.descricao(),
                output.ativo()
        );
    }

    public static PlanoResponseDTO toResponse(ListarPlanosOutput output){

        return build(
                output.id(),
                output.nome(),
                output.valor(),
                output.descricao(),
                output.ativo()
        );
    }

    public static PlanoResponseDTO toResponse(AtualizarPlanoOutput output){
        return build(
                output.id(),
                output.nome(),
                output.valor(),
                output.descricao(),
                output.ativo()
        );
    }
}
