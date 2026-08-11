package com.br.integramove.application.aluno.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoInput(

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(
                regexp = "^\\d{5}-?\\d{3}$",
                message = "CEP inválido. Utilize o formato 12345-678 ou 12345678."
        )
        String cep,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve possuir a sigla com 2 caracteres.")
        @Pattern(
                regexp = "^[A-Z]{2}$",
                message = "Estado inválido. Utilize a sigla da UF, por exemplo: SP."
        )
        String estado,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(min = 2, max = 100, message = "A cidade deve possuir entre 2 e 100 caracteres.")
        String cidade,

        @NotBlank(message = "A rua é obrigatória")
        @Size(min = 3, max = 150, message = "A rua deve possuir entre 3 e 150 caracteres.")
        String rua,

        @NotBlank(message = "O número é obrigatório")
        @Size(max = 10, message = "O número deve possuir no máximo 10 caracteres.")
        @Pattern(
                regexp = "^(\\d+|S/N)$",
                message = "Número inválido. Utilize apenas números ou 'S/N'."
        )
        String numero,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(min = 2, max = 100, message = "O bairro deve possuir entre 2 e 100 caracteres.")
        String bairro

) {
}
