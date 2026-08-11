package com.br.integramove.application.aluno.services;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Gera senhas temporárias aleatórias e seguras para serem entregues ao aluno
 * no momento do cadastro (ex.: pela recepção da academia).
 */
@Component
public class GeradorSenhaTemporaria {

    // Alfabeto sem caracteres ambíguos (0/O, 1/l/I).
    private static final String ALFABETO = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
    private static final int TAMANHO_MINIMO = 8;
    private static final int TAMANHO_MAXIMO = 10;

    private final SecureRandom random = new SecureRandom();

    public String gerar() {
        int tamanho = TAMANHO_MINIMO + random.nextInt(TAMANHO_MAXIMO - TAMANHO_MINIMO + 1);

        StringBuilder senha = new StringBuilder(tamanho);

        for (int i = 0; i < tamanho; i++) {
            senha.append(ALFABETO.charAt(random.nextInt(ALFABETO.length())));
        }

        return senha.toString();
    }
}