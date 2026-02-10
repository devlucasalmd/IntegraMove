package com.br.integramove.domain.aluno;

import com.br.integramove.api.exception.aluno.EmailInvalidoException;

public class Email {

    private final String email;

    public Email(String email){
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new EmailInvalidoException();
        }
        this.email = email;
    }

    public String toString() {
        return email;
    }

}
