package com.br.integramove.application.contaReceber.services;

import com.br.integramove.application.contaReceber.ContaReceberRepository;
import com.br.integramove.application.contaReceber.inputs.ReceberContaReceberInput;
import com.br.integramove.application.contaReceber.outputs.ContaReceberOutput;
import com.br.integramove.domain.contasReceber.ContaReceber;
import com.br.integramove.domain.contasReceber.ContaReceberId;
import org.springframework.stereotype.Service;

@Service
public class ReceberContaReceber {

    private final ContaReceberRepository contaReceberRepository;

    public ReceberContaReceber(ContaReceberRepository contaReceberRepository) {
        this.contaReceberRepository = contaReceberRepository;
    }

    public ContaReceberOutput executar(String contaReceberId, ReceberContaReceberInput input) {
        ContaReceber contaReceber = contaReceberRepository.buscarPorId(
                ContaReceberId.from(contaReceberId)
        ).orElseThrow(() -> new RuntimeException("Conta a receber não encontrada"));

        contaReceber.receber(
                input.dataRecebimento(),
                input.formaPagamento()
        );

        ContaReceber contaReceberSalva = contaReceberRepository.salvar(contaReceber);

        return toOutput(contaReceberSalva);
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