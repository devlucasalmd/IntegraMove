package com.br.integramove.infrastructure.persistence.aluno;

import com.br.integramove.domain.aluno.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
public class AlunoEntity {
    @Id
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String genero;
    private String telefone;
    private String email;
    private boolean ativo;
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String bairro;
}
