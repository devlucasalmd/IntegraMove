package com.br.integramove.domain.valueobjects;

import com.br.integramove.api.exception.aluno.EmailInvalidoException;

import java.util.Locale;
import java.util.Objects;

public final class Email {

    private final String value;


    private Email(String email) {

        if (email == null || email.isBlank()) {
            throw new EmailInvalidoException();
        }

        String emailNormalizado = email
                .trim()
                .toLowerCase(Locale.ROOT);


        if (!emailNormalizado.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {
            throw new EmailInvalidoException();
        }

        this.value = emailNormalizado;
    }


    public static Email of(String value) {
        return new Email(value);
    }


    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Email email)) {
            return false;
        }

        return value.equals(email.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}