package com.br.integramove.application.contaReceber.services;

import com.br.integramove.application.contaReceber.ContaReceberRepository;
import com.br.integramove.application.contaReceber.inputs.CriarContaReceberInput;
import com.br.integramove.application.contaReceber.outputs.ContaReceberOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.financas.contasReceber.ContaReceber;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class CriarContaReceber {

    private final ContaReceberRepository contaReceberRepository;

    public CriarContaReceber(ContaReceberRepository contaReceberRepository) {
        this.contaReceberRepository = contaReceberRepository;
    }

    public ContaReceberOutput executar(CriarContaReceberInput input) {
        AlunoId alunoId = input.alunoId() != null && !input.alunoId().isBlank()
                ? AlunoId.from(input.alunoId())
                : null;

        PlanoId planoId = input.planoId() != null && !input.planoId().isBlank()
                ? PlanoId.from(input.planoId())
                : null;

        ContaReceber contaReceber = ContaReceber.criar(
                input.descricao(),
                input.categoria(),
                input.valor(),
                input.dataVencimento(),
                alunoId,
                planoId,
                input.observacoes()
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
