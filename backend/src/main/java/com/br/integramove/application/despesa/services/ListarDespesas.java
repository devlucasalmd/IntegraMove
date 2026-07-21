package com.br.integramove.application.despesa.services;

import com.br.integramove.application.despesa.DespesaRepository;
import com.br.integramove.application.despesa.outputs.DespesaOutput;
import com.br.integramove.domain.despesa.Despesa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDespesas {

    private final DespesaRepository despesaRepository;

    public ListarDespesas(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<DespesaOutput> executar() {
        return despesaRepository.listar()
                .stream()
                .map(this::toOutput)
                .toList();
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
