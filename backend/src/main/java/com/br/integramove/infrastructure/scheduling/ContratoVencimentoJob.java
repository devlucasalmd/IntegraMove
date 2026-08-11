package com.br.integramove.infrastructure.scheduling;

import com.br.integramove.application.contrato.services.EncerrarContratosVencidos;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Adapter de infraestrutura que dispara diariamente a verificação de contratos
 * vencidos sem renovação automática. A regra de negócio em si vive em
 * {@link EncerrarContratosVencidos}, na camada de aplicação — este job apenas
 * aciona o caso de uso no horário configurado.
 */
@Component
public class ContratoVencimentoJob {

    private final EncerrarContratosVencidos encerrarContratosVencidos;

    public ContratoVencimentoJob(EncerrarContratosVencidos encerrarContratosVencidos) {
        this.encerrarContratosVencidos = encerrarContratosVencidos;
    }

    @Scheduled(cron = "${contrato.vencimento.cron:0 0 1 * * *}")
    public void verificarContratosVencidos() {
        encerrarContratosVencidos.executar();
    }
}