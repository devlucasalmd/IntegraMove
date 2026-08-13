package com.br.integramove.application.contrato.services;

import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.Contrato;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarContratosPorAluno {

    private final ContratoRepository contratoRepository;

    public ListarContratosPorAluno(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public List<ContratoOutput> listar(String alunoId) {

        List<Contrato> contratos = contratoRepository.listarPorAlunoId(AlunoId.from(alunoId));

        return contratos.stream()
                .map(contrato -> new ContratoOutput(
                        contrato.getId().getValue().toString(),
                        contrato.getAlunoId().getValue().toString(),
                        contrato.getVendaId() != null ? contrato.getVendaId().getValue().toString() : null,
                        contrato.getPlanoId().getValue().toString(),
                        contrato.getDataInicio(),
                        contrato.getDataFim(),
                        contrato.getDiaVencimento(),
                        contrato.getPermiteRenovacaoAutomatica(),
                        contrato.getStatus(),
                        contrato.getDocumentoUrl(),
                        contrato.getCreatedAt(),
                        contrato.getUpdatedAt()
                ))
                .toList();
    }
}