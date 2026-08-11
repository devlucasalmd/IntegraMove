package com.br.integramove.application.usuario.inputs;

import com.br.integramove.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.*;

public record AtualizarUsuarioInput(

        @NotBlank(message = "O identificador do usuário é obrigatório.")
        @Pattern(
                regexp = "^[0-9a-fA-F\\-]{36}$",
                message = "Identificador inválido."
        )
        String id,

        @NotBlank(message = "O nome é obrigatório.")
        @Size(
                min = 3,
                max = 100,
                message = "O nome deve possuir entre 3 e 100 caracteres."
        )
        String nome,

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

        @NotNull(message = "O perfil é obrigatório.")
        PerfilUsuario perfil,

        @NotNull(message = "O status ativo é obrigatório.")
        Boolean ativo
) {}
