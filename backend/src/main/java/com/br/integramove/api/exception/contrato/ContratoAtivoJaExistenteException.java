package com.br.integramove.api.exception.contrato;

public class ContratoAtivoJaExistenteException extends RuntimeException {

    public ContratoAtivoJaExistenteException() {
        super("Aluno já possui um contrato ativo.");
    }
}