package com.br.integramove.application.despesa.services;

import com.br.integramove.application.despesa.DespesaRepository;
import com.br.integramove.application.despesa.inputs.CriarDespesaInput;
import com.br.integramove.application.despesa.outputs.DespesaOutput;
import com.br.integramove.domain.despesa.Despesa;
import org.springframework.stereotype.Service;

@Service
public class CriarDespesa {

    private final DespesaRepository despesaRepository;

    public CriarDespesa(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public DespesaOutput executar(CriarDespesaInput input) {
        Despesa despesa = Despesa.criar(
                input.descricao(),
                input.categoria(),
                input.valor(),
                input.dataVencimento(),
                input.fornecedor(),
                input.observacoes()
        );

        Despesa despesaSalva = despesaRepository.salvar(despesa);

        return toOutput(despesaSalva);
    }

    private DespesaOutput toOutput(Despesa despesa) {
        return new DespesaOutput(
                despesa.getId().getValue().toString(),
                despesa.getDescricao(),
                despesa.getCategoria() != null ? despesa.getCategoria().name() : null,
                despesa.getValor(),
                despesa.getDataVencimento(),
                despesa.getDataPagamento(),
                despesa.getFormaPagamento() != null ? despesa.getFormaPagamento().name() : null,
                despesa.getStatus() != null ? despesa.getStatus().name() : null,
                despesa.getFornecedor(),
                despesa.getObservacoes()
        );
    }
}
