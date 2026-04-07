package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.PagamentoRequestDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.application.pagamento.BuscarPagamentoOutput;
import com.br.integramove.application.pagamento.CriarPagamentoInput;
import com.br.integramove.application.pagamento.CriarPagamentoOutput;
import com.br.integramove.application.pagamento.ListarPagamentosOutput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class PagamentoMapper {

    public static PagamentoResponseDTO build(
            String id,
            String formaPagamento,
            BigDecimal valor,
            LocalDate data,
            String status
    ){
      return new PagamentoResponseDTO(
              id, formaPagamento, valor, data, status
      );
    }

    public static CriarPagamentoInput toInput(PagamentoRequestDTO dto){
        return new CriarPagamentoInput(
                dto.formaPagamento(),
                dto.valor(),
                dto.data(),
                dto.status()
        );
    }

    public static PagamentoResponseDTO toResponse(CriarPagamentoOutput output){
        return build(
                output.id(),
                output.formaPagamento(),
                output.valor(),
                output.data(),
                output.status()
        );
    }

    public static PagamentoResponseDTO toResponse(BuscarPagamentoOutput output){
        return build(
                output.id(),
                output.formaPagamento(),
                output.valor(),
                output.data(),
                output.status()
        );
    }

    public static PagamentoResponseDTO toResponse(ListarPagamentosOutput output){
        return build(
                output.id(),
                output.formaPagamento(),
                output.valor(),
                output.data(),
                output.status()
        );
    }
}
