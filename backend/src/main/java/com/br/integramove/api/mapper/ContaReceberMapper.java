package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.AtualizarContaReceberRequestDTO;
import com.br.integramove.api.dto.request.ContaReceberRequestDTO;
import com.br.integramove.api.dto.request.ReceberContaReceberRequestDTO;
import com.br.integramove.api.dto.response.ContaReceberResponseDTO;
import com.br.integramove.application.contaReceber.inputs.AtualizarContaReceberInput;
import com.br.integramove.application.contaReceber.inputs.CriarContaReceberInput;
import com.br.integramove.application.contaReceber.inputs.ReceberContaReceberInput;
import com.br.integramove.application.contaReceber.outputs.ContaReceberOutput;
import org.springframework.stereotype.Component;

@Component
public class ContaReceberMapper {

    public CriarContaReceberInput toInput(ContaReceberRequestDTO dto) {
        return new CriarContaReceberInput(
                dto.descricao(),
                dto.categoria(),
                dto.valor(),
                dto.dataVencimento(),
                dto.alunoId(),
                dto.planoId(),
                dto.observacoes()
        );
    }

    public AtualizarContaReceberInput toInput(AtualizarContaReceberRequestDTO dto) {
        return new AtualizarContaReceberInput(
                dto.descricao(),
                dto.categoria(),
                dto.valor(),
                dto.dataVencimento(),
                dto.alunoId(),
                dto.planoId(),
                dto.observacoes()
        );
    }

    public ReceberContaReceberInput toInput(ReceberContaReceberRequestDTO dto) {
        return new ReceberContaReceberInput(
                dto.dataRecebimento(),
                dto.formaPagamento()
        );
    }

    public ContaReceberResponseDTO toResponse(ContaReceberOutput output) {
        return new ContaReceberResponseDTO(
                output.id(),
                output.descricao(),
                output.categoria(),
                output.valor(),
                output.dataVencimento(),
                output.dataRecebimento(),
                output.formaPagamento(),
                output.status(),
                output.alunoId(),
                output.planoId(),
                output.observacoes()
        );
    }
}
