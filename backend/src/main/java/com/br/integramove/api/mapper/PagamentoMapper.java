package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.PagamentoRequestDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.application.pagamento.outputs.BuscarPagamentoOutput;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.CriarPagamentoOutput;
import com.br.integramove.application.pagamento.outputs.ListarPagamentosOutput;
import com.br.integramove.application.pagamento.outputs.ListarPagamentosPorAlunoOutput;
import com.br.integramove.domain.pagamento.FormaPagamento;
import com.br.integramove.domain.pagamento.StatusPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class PagamentoMapper {

    public static PagamentoResponseDTO build(
            String id,
            String alunoId,
            String planoId,
            BigDecimal valor,
            LocalDate dataPagamento,
            LocalDate dataVencimento,
            FormaPagamento formaPagamento,
            StatusPagamento status
    ){
      return new PagamentoResponseDTO(
              id, alunoId, planoId, valor, dataPagamento, dataVencimento, formaPagamento, status
      );
    }

    public static CriarPagamentoInput toInput(PagamentoRequestDTO dto){
        return new CriarPagamentoInput(
                dto.alunoId(),
                dto.dataPagamento(),
                dto.dataVencimento(),
                dto.formaPagamento(),
                dto.status()
        );
    }

    public static PagamentoResponseDTO toResponse(CriarPagamentoOutput output){
        return build(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.valor(),
                output.dataPagamento(),
                output.dataVencimento(),
                output.formaPagamento(),
                output.status()
        );
    }

    public static PagamentoResponseDTO toResponse(BuscarPagamentoOutput output){
        return build(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.valor(),
                output.dataPagamento(),
                output.dataVencimento(),
                output.formaPagamento(),
                output.status()
        );
    }

    public static PagamentoResponseDTO toResponse(ListarPagamentosOutput output){
        return build(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.valor(),
                output.dataPagamento(),
                output.dataVencimento(),
                output.formaPagamento(),
                output.status()
        );
    }

    public static PagamentoResponseDTO toResponse(ListarPagamentosPorAlunoOutput output) {
        return build(
                output.id(),
                output.alunoId(),
                output.planoId(),
                output.valor(),
                output.dataPagamento(),
                output.dataVencimento(),
                output.formaPagamento(),
                output.status()
        );
    }
}
