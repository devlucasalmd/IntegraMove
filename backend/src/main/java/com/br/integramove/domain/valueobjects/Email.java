package com.br.integramove.domain.valueobjects;

import com.br.integramove.api.exception.aluno.EmailInvalidoException;

public class Email {

    private final String email;

    public Email(String email){
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new EmailInvalidoException();
        }
        this.email = email;
    }

    public static Email of(String value) { return new Email(value); }

    public String toString() {
        return email;
    }

    public String getValue() {
        return email;
    }
}
