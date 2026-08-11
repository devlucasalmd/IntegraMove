package com.br.integramove.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Disponibiliza um {@link PasswordEncoder} (BCrypt) para hashing de senhas.
 *
 * Observação: apenas a dependência spring-security-crypto é usada aqui.
 * Não há Spring Security (filter chain, autenticação via sessão/JWT, etc.)
 * configurado no projeto — isso está fora do escopo atual.
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}