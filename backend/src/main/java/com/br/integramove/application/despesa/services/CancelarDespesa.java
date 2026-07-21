package com.br.integramove.application.despesa.services;

import com.br.integramove.application.despesa.DespesaRepository;
import com.br.integramove.application.despesa.outputs.DespesaOutput;
import com.br.integramove.domain.despesa.Despesa;
import com.br.integramove.domain.despesa.DespesaId;
import org.springframework.stereotype.Service;

@Service
public class CancelarDespesa {

    private final DespesaRepository despesaRepository;

    public CancelarDespesa(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public DespesaOutput executar(String despesaId) {
        Despesa despesa = despesaRepository.buscarPorId(DespesaId.from(despesaId))
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        despesa.cancelar();

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
