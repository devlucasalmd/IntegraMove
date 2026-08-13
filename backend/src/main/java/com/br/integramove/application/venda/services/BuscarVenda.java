package com.br.integramove.application.venda.services;

import com.br.integramove.api.exception.venda.VendaNaoEncontradaException;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.application.venda.VendaRepository;
import com.br.integramove.application.venda.outputs.VendaOutput;
import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.venda.Venda;
import com.br.integramove.domain.venda.VendaId;
import org.springframework.stereotype.Service;

@Service
public class BuscarVenda {

    private final VendaRepository vendaRepository;
    private final PlanoRepository planoRepository;

    public BuscarVenda(VendaRepository vendaRepository, PlanoRepository planoRepository) {
        this.vendaRepository = vendaRepository;
        this.planoRepository = planoRepository;
    }

    public VendaOutput buscar(String id) {

        VendaId vendaId = VendaId.from(id);

        Venda venda = vendaRepository.buscarPorId(vendaId)
                .orElseThrow(() -> new VendaNaoEncontradaException(vendaId));

        Periodicidade periodicidade = null;
        if (venda.getPlanoId() != null) {
            periodicidade = planoRepository.buscarPorId(venda.getPlanoId())
                    .map(Plano::getPeriodicidade)
                    .orElse(null);
        }

        return new VendaOutput(
                venda.getId().getValue().toString(),
                venda.getAlunoId().getValue().toString(),
                venda.getTipo(),
                venda.getPlanoId() != null ? venda.getPlanoId().getValue().toString() : null,
                venda.getDescricao(),
                venda.getValor(),
                periodicidade,
                venda.getDataVenda(),
                venda.getStatus(),
                null, // Contrato.vendaId permite a busca, mas ainda não há ContratoRepository.buscarPorVendaId; pendente
                venda.getCreatedAt(),
                venda.getUpdatedAt()
        );
    }
}