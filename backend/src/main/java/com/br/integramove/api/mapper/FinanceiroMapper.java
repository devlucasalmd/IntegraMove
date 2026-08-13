package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.RegistrarPagamentoRequestDTO;
import com.br.integramove.api.dto.response.FinanceiroResponseDTO;
import com.br.integramove.application.financeiro.inputs.RegistrarPagamentoInput;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroMapper {

    public static RegistrarPagamentoInput toInput(String financeiroId, RegistrarPagamentoRequestDTO dto) {
        return new RegistrarPagamentoInput(
                financeiroId,
                dto.formaPagamento(),
                dto.dataPagamento()
        );
    }

    public static FinanceiroResponseDTO toResponse(FinanceiroOutput output) {
        return new FinanceiroResponseDTO(
                output.id(),
                output.alunoId(),
                output.vendaId(),
                output.contratoId(),
                output.valor(),
                output.numeroParcela(),
                output.totalParcelas(),
                output.dataVencimento(),
                output.dataPagamento(),
                output.status(),
                output.formaPagamento(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}