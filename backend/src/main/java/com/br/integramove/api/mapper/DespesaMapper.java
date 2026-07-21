package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.AtualizarDespesaRequestDTO;
import com.br.integramove.api.dto.request.DespesaRequestDTO;
import com.br.integramove.api.dto.request.PagarDespesaRequestDTO;
import com.br.integramove.api.dto.response.DespesaResponseDTO;
import com.br.integramove.application.despesa.inputs.AtualizarDespesaInput;
import com.br.integramove.application.despesa.inputs.CriarDespesaInput;
import com.br.integramove.application.despesa.inputs.PagarDespesaInput;
import com.br.integramove.application.despesa.outputs.DespesaOutput;
import org.springframework.stereotype.Component;

@Component
public class DespesaMapper {

    public CriarDespesaInput toInput(DespesaRequestDTO dto) {
        return new CriarDespesaInput(
                dto.descricao(),
                dto.categoria(),
                dto.valor(),
                dto.dataVencimento(),
                dto.fornecedor(),
                dto.observacoes()
        );
    }

    public AtualizarDespesaInput toInput(AtualizarDespesaRequestDTO dto) {
        return new AtualizarDespesaInput(
                dto.descricao(),
                dto.categoria(),
                dto.valor(),
                dto.dataVencimento(),
                dto.fornecedor(),
                dto.observacoes()
        );
    }

    public PagarDespesaInput toInput(PagarDespesaRequestDTO dto) {
        return new PagarDespesaInput(
                dto.dataPagamento(),
                dto.formaPagamento()
        );
    }

    public DespesaResponseDTO toResponse(DespesaOutput output) {
        return new DespesaResponseDTO(
                output.id(),
                output.descricao(),
                output.categoria(),
                output.valor(),
                output.dataVencimento(),
                output.dataPagamento(),
                output.formaPagamento(),
                output.status(),
                output.fornecedor(),
                output.observacoes()
        );
    }
}
