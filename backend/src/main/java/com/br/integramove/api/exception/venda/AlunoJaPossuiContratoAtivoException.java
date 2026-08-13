package com.br.integramove.api.exception.venda;

public class AlunoJaPossuiContratoAtivoException extends RuntimeException {

    public AlunoJaPossuiContratoAtivoException() {
        super("Aluno já possui um contrato ativo. Encerre ou cancele o contrato vigente antes de registrar uma nova venda de plano.");
    }
}