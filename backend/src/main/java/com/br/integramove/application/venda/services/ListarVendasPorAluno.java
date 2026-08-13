package com.br.integramove.application.venda.services;

import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.application.venda.VendaRepository;
import com.br.integramove.application.venda.outputs.VendaOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.Periodicidade;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.venda.Venda;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVendasPorAluno {

    private final VendaRepository vendaRepository;
    private final PlanoRepository planoRepository;

    public ListarVendasPorAluno(VendaRepository vendaRepository, PlanoRepository planoRepository) {
        this.vendaRepository = vendaRepository;
        this.planoRepository = planoRepository;
    }

    public List<VendaOutput> listar(String alunoId) {

        List<Venda> vendas = vendaRepository.listarPorAlunoId(AlunoId.from(alunoId));

        return vendas.stream()
                .map(venda -> {
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
                            null,
                            venda.getCreatedAt(),
                            venda.getUpdatedAt()
                    );
                })
                .toList();
    }
}