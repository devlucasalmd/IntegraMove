package com.br.integramove.application.contaReceber.services;

import com.br.integramove.application.contaReceber.ContaReceberRepository;
import com.br.integramove.application.contaReceber.outputs.ContaReceberOutput;
import com.br.integramove.domain.contasReceber.ContaReceber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarContasReceber {

    private final ContaReceberRepository contaReceberRepository;

    public ListarContasReceber(ContaReceberRepository contaReceberRepository) {
        this.contaReceberRepository = contaReceberRepository;
    }

    public List<ContaReceberOutput> executar() {
        return contaReceberRepository.listar()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private ContaReceberOutput toOutput(ContaReceber contaReceber) {
        return new ContaReceberOutput(
                contaReceber.getId().getValue().toString(),
                contaReceber.getDescricao(),
                contaReceber.getCategoria() != null ? contaReceber.getCategoria().name() : null,
                contaReceber.getValor(),
                contaReceber.getDataVencimento(),
                contaReceber.getDataRecebimento(),
                contaReceber.getFormaPagamento() != null ? contaReceber.getFormaPagamento().name() : null,
                contaReceber.getStatus() != null ? contaReceber.getStatus().name() : null,
                contaReceber.getAlunoId() != null ? contaReceber.getAlunoId().getValue().toString() : null,
                contaReceber.getPlanoId() != null ? contaReceber.getPlanoId().getValue().toString() : null,
                contaReceber.getObservacoes()
        );
    }
}
