package com.br.integramove.application.venda.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.api.exception.venda.AlunoJaPossuiContratoAtivoException;
import com.br.integramove.api.exception.venda.DiaVencimentoObrigatorioException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.application.contrato.inputs.CriarContratoInput;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import com.br.integramove.application.contrato.services.CriarContrato;
import com.br.integramove.application.financeiro.inputs.GerarCobrancasInput;
import com.br.integramove.application.financeiro.services.GerarCobrancasVenda;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.application.venda.VendaRepository;
import com.br.integramove.application.venda.inputs.CriarVendaInput;
import com.br.integramove.application.venda.outputs.VendaOutput;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.enums.StatusContrato;
import com.br.integramove.domain.enums.TipoVenda;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.venda.Venda;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Toda Venda deve gerar cobrança(s) pendente(s) no Financeiro — a implementação
 * do módulo Financeiro em si é a próxima etapa. Esta classe mantém VendaOutput
 * expondo os dados necessários para isso (valor, periodicidade do plano quando
 * aplicável) sem acoplar Venda à entidade Financeiro.
 */
@Service
public class CriarVenda {

    private final VendaRepository vendaRepository;
    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final ContratoRepository contratoRepository;
    private final CriarContrato criarContrato;
    private final GerarCobrancasVenda gerarCobrancasVenda;

    public CriarVenda(
            VendaRepository vendaRepository,
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository,
            ContratoRepository contratoRepository,
            CriarContrato criarContrato,
            GerarCobrancasVenda gerarCobrancasVenda
    ) {
        this.vendaRepository = vendaRepository;
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.contratoRepository = contratoRepository;
        this.criarContrato = criarContrato;
        this.gerarCobrancasVenda = gerarCobrancasVenda;
    }

    public VendaOutput criar(CriarVendaInput input) {

        AlunoId alunoId = AlunoId.from(input.alunoId());
        alunoRepository.buscarPorId(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        PlanoId planoId = null;
        Plano plano = null;
        BigDecimal valor;

        if (input.tipo() == TipoVenda.PLANO) {
            if (input.planoId() == null) {
                throw new IllegalArgumentException("Plano é obrigatório para venda do tipo PLANO");
            }
            if (input.diaVencimento() == null) {
                throw new DiaVencimentoObrigatorioException();
            }

            PlanoId planoIdResolvido = PlanoId.from(input.planoId());
            planoId = planoIdResolvido;
            plano = planoRepository.buscarPorId(planoIdResolvido)
                    .orElseThrow(() -> new PlanoNaoEncontradoException(planoIdResolvido));
            valor = plano.getValor();

            contratoRepository.buscarPorAlunoIdEStatus(alunoId, StatusContrato.ATIVO)
                    .ifPresent(contratoAtivo -> { throw new AlunoJaPossuiContratoAtivoException(); });
        } else {
            valor = input.valor();
        }

        Venda venda = Venda.criar(
                alunoId,
                input.tipo(),
                planoId,
                input.descricao(),
                valor,
                input.dataVenda()
        );

        Venda vendaSalva = vendaRepository.salvar(venda);

        String contratoId = null;
        if (input.tipo() == TipoVenda.PLANO) {
            ContratoOutput contratoOutput = criarContrato.criar(new CriarContratoInput(
                    alunoId.getValue().toString(),
                    vendaSalva.getId().getValue().toString(),
                    planoId.getValue().toString(),
                    input.dataVenda(),
                    input.diaVencimento(),
                    input.permiteRenovacaoAutomatica()
            ));
            contratoId = contratoOutput.id();

            gerarCobrancasVenda.gerar(new GerarCobrancasInput(
                    alunoId.getValue().toString(),
                    vendaSalva.getId().getValue().toString(),
                    input.tipo(),
                    valor,
                    input.dataVenda(),
                    contratoId,
                    plano.getPeriodicidade(),
                    contratoOutput.dataInicio(),
                    contratoOutput.dataFim(),
                    input.diaVencimento()
            ));
        } else {
            gerarCobrancasVenda.gerar(new GerarCobrancasInput(
                    alunoId.getValue().toString(),
                    vendaSalva.getId().getValue().toString(),
                    input.tipo(),
                    valor,
                    input.dataVenda(),
                    null,
                    null,
                    null,
                    null,
                    null
            ));
        }

        return new VendaOutput(
                vendaSalva.getId().getValue().toString(),
                vendaSalva.getAlunoId().getValue().toString(),
                vendaSalva.getTipo(),
                vendaSalva.getPlanoId() != null ? vendaSalva.getPlanoId().getValue().toString() : null,
                vendaSalva.getDescricao(),
                vendaSalva.getValor(),
                plano != null ? plano.getPeriodicidade() : null,
                vendaSalva.getDataVenda(),
                vendaSalva.getStatus(),
                contratoId,
                vendaSalva.getCreatedAt(),
                vendaSalva.getUpdatedAt()
        );
    }
}