package com.br.integramove.application.contrato.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.enums.StatusContrato;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Aplica a regra de vencimento sem renovação automática: contratos ATIVO cuja
 * dataFim foi atingida e que não permitem renovação automática são encerrados,
 * e o Aluno correspondente é inativado. Disparado periodicamente pelo job de
 * infraestrutura em infrastructure/scheduling.
 */
@Service
public class EncerrarContratosVencidos {

    private final ContratoRepository contratoRepository;
    private final AlunoRepository alunoRepository;

    public EncerrarContratosVencidos(ContratoRepository contratoRepository, AlunoRepository alunoRepository) {
        this.contratoRepository = contratoRepository;
        this.alunoRepository = alunoRepository;
    }

    public void executar() {

        var contratosVencidos = contratoRepository.listarVencidosSemRenovacaoAutomatica(
                StatusContrato.ATIVO,
                LocalDate.now()
        );

        for (Contrato contrato : contratosVencidos) {
            contrato.encerrar();
            contratoRepository.salvar(contrato);

            Aluno aluno = alunoRepository.buscarPorId(contrato.getAlunoId())
                    .orElseThrow(() -> new AlunoNaoEncontradoException(contrato.getAlunoId()));
            aluno.desativar();
            alunoRepository.salvar(aluno);
        }
    }
}