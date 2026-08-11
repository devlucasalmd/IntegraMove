package com.br.integramove.application.aluno.inputs;

import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;
import com.br.integramove.domain.enums.Genero;
import com.br.integramove.domain.enums.StatusAluno;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
public record CriarAlunoInput(

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

        @NotNull(message = "E-mail é obrigatório")
        Email email,

        @NotNull(message = "O status é obrigatório")
        StatusAluno status,

        @NotBlank(message = "O plano é obrigatório")
        String planoId,

        @NotNull(message = "O endereço é obrigatório")
        @Valid
        EnderecoInput endereco

) {
}