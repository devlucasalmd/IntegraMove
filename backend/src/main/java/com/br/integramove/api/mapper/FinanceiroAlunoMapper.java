package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.response.FinanceiroAlunoResponseDTO;
import com.br.integramove.application.aluno.outputs.BuscarFinanceiroAlunoOutput;

public class FinanceiroAlunoMapper {

    public static FinanceiroAlunoResponseDTO toResponse(BuscarFinanceiroAlunoOutput output) {
        return new FinanceiroAlunoResponseDTO(
                output.alunoId(),
                output.nomeAluno(),
                output.planoId(),
                output.nomePlano(),
                output.valorPlano(),
                output.statusFinanceiro(),
                output.totalPago(),
                output.totalEmAberto(),
                output.totalVencido(),
                output.ultimoPagamento(),
                output.proximoVencimento()
        );
    }

}
