package com.br.integramove.api.exception.aluno;

public class CpfJaCadastradoException extends RuntimeException {

    public CpfJaCadastradoException(){
        super("CPF já cadastrado no sistema");
    }
}
