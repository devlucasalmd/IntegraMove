package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.PagamentoRequestDTO;
import com.br.integramove.api.dto.request.PagarPagamentoRequestDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.inputs.PagarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.PagamentoOutput;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public CriarPagamentoInput toInput(String alunoId, PagamentoRequestDTO dto) {
        return new CriarPagamentoInput(
                alunoId,
                dto.planoId(),
                dto.valor(),
                dto.dataVencimento(),
                dto.observacoes()
        );
    }

    public PagarPagamentoInput toInput(PagarPagamentoRequestDTO dto) {
        return new PagarPagamentoInput(
                dto.formaPagamento(),
                dto.dataPagamento()
        );
    }

    public PagamentoResponseDTO toResponse(PagamentoOutput output) {
        return new PagamentoResponseDTO(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.valor(),
                output.dataVencimento(),
                output.dataPagamento(),
                output.formaPagamento(),
                output.status(),
                output.observacoes()
        );
    }
}
