package com.br.integramove.application.usuario.inputs;

import com.br.integramove.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record CriarUsuarioInput(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(
                min = 3,
                max = 100,
                message = "O nome deve possuir entre 3 e 100 caracteres."
        )
        String nome,

        @NotBlank(message = "O CPF é obrigatório.")
        @CPF(message = "CPF inválido.")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido."
        )
        String telefone,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        @Size(
                max = 150,
                message = "O e-mail deve possuir no máximo 150 caracteres."
        )
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(
                min = 8,
                max = 100,
                message = "A senha deve possuir entre 8 e 100 caracteres."
        )
        String senha,

        @NotNull(message = "O perfil é obrigatório.")
        PerfilUsuario perfil
) {}
