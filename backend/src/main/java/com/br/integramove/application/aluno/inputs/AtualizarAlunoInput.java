package com.br.integramove.application.aluno.inputs;

import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;
import com.br.integramove.domain.valueobjects.Cpf;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AtualizarAlunoInput(

        @NotBlank(message = "O ID é obrigatório")
        String id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        @NotNull(message = "CPF é obrigatório")
        Cpf cpf,

        @NotNull(message = "O gênero é obrigatório")
        Genero genero,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        com.br.integramove.domain.valueobjects.Email email,

        @NotNull(message = "O status é obrigatório")
        StatusAluno status,

        @NotNull(message = "O endereço é obrigatório")
        @Valid
        EnderecoInput endereco

) {
}
