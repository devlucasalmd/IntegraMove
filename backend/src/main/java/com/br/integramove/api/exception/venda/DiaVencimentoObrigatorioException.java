package com.br.integramove.api.exception.venda;

public class DiaVencimentoObrigatorioException extends RuntimeException {

    public DiaVencimentoObrigatorioException() {
        super("Dia de vencimento é obrigatório para venda do tipo PLANO.");
    }
}