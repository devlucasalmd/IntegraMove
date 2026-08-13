package com.br.integramove.application.contrato.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.api.exception.contrato.ContratoAtivoJaExistenteException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.application.contrato.DadosDocumentoContrato;
import com.br.integramove.application.contrato.GeradorDocumentoContratoPort;
import com.br.integramove.application.contrato.inputs.CriarContratoInput;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.enums.StatusContrato;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.venda.VendaId;
import org.springframework.stereotype.Service;

/**
 * Representa a criação do Contrato a partir do fechamento de uma Venda de Plano
 * recorrente — CriarVenda invoca este caso de uso repassando o id da Venda que
 * está sendo criada. vendaId é opcional aqui para não quebrar a criação direta
 * de Contrato via API (POST /contratos), que não passa por uma Venda.
 */
@Service
public class CriarContrato {

    private final ContratoRepository contratoRepository;
    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final GeradorDocumentoContratoPort geradorDocumentoContrato;

    public CriarContrato(
            ContratoRepository contratoRepository,
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository,
            GeradorDocumentoContratoPort geradorDocumentoContrato
    ) {
        this.contratoRepository = contratoRepository;
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.geradorDocumentoContrato = geradorDocumentoContrato;
    }

    public ContratoOutput criar(CriarContratoInput input) {

        AlunoId alunoId = AlunoId.from(input.alunoId());
        PlanoId planoId = PlanoId.from(input.planoId());
        VendaId vendaId = input.vendaId() != null ? VendaId.from(input.vendaId()) : null;

        Aluno aluno = alunoRepository.buscarPorId(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        Plano plano = planoRepository.buscarPorId(planoId)
                .orElseThrow(() -> new PlanoNaoEncontradoException(planoId));

        contratoRepository.buscarPorAlunoIdEStatus(alunoId, StatusContrato.ATIVO)
                .ifPresent(c -> { throw new ContratoAtivoJaExistenteException(); });

        Contrato contrato = Contrato.criar(
                alunoId,
                vendaId,
                planoId,
                input.dataInicio(),
                plano.getDuracaoDias(),
                input.diaVencimento(),
                input.permiteRenovacaoAutomatica()
        );

        String documentoUrl = geradorDocumentoContrato.gerar(new DadosDocumentoContrato(
                contrato.getId().getValue().toString(),
                aluno.getNome(),
                plano.getNome(),
                plano.getValor(),
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getDiaVencimento()
        ));
        contrato.anexarDocumento(documentoUrl);

        Contrato salvo = contratoRepository.salvar(contrato);

        return new ContratoOutput(
                salvo.getId().getValue().toString(),
                salvo.getAlunoId().getValue().toString(),
                salvo.getVendaId() != null ? salvo.getVendaId().getValue().toString() : null,
                salvo.getPlanoId().getValue().toString(),
                salvo.getDataInicio(),
                salvo.getDataFim(),
                salvo.getDiaVencimento(),
                salvo.getPermiteRenovacaoAutomatica(),
                salvo.getStatus(),
                salvo.getDocumentoUrl(),
                salvo.getCreatedAt(),
                salvo.getUpdatedAt()
        );
    }
}