package com.br.integramove.domain.aluno;

import com.br.integramove.api.exception.aluno.CpfInvalidoException;
import com.br.integramove.api.exception.aluno.EmailInvalidoException;
import com.br.integramove.api.exception.aluno.NomeInvalidoException;
import com.br.integramove.domain.plano.PlanoId;

import java.time.LocalDate;

public class Aluno {

    private final AlunoId id;
    private String nome;
    private LocalDate dataNascimento;
    private Cpf cpf;
    private Genero genero;
    private String telefone;
    private Email email;
    private StatusAluno status;
    private PlanoId planoId;
    private Endereco endereco;


    public Aluno(
            AlunoId id,
            String nome,
            LocalDate dataNascimento,
            Cpf cpf,
            Genero genero,
            String telefone,
            Email email,
            StatusAluno status,
            PlanoId planoId,
            Endereco endereco

            ) {

        if(id == null) throw new IllegalArgumentException("Id obrigatorio");
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if(cpf == null) throw new CpfInvalidoException();
        if(email == null) throw new EmailInvalidoException();

        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.status = status != null ? status : StatusAluno.ATIVO;
        this.planoId = planoId;
        this.endereco = endereco;

    }

    public AlunoId getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Cpf getCpf() { return cpf; }
    public Genero getGenero() { return genero; }
    public String getTelefone() { return telefone; }
    public Email getEmail() { return email; }
    public StatusAluno getStatus() { return status; }
    public Endereco getEndereco() { return endereco; }
    public PlanoId getPlanoId() { return planoId; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new NomeInvalidoException();
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(Cpf cpf) {
        if (cpf == null) throw new CpfInvalidoException();
        this.cpf = cpf;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(Email email) {
        if (email == null) throw new EmailInvalidoException();
        this.email = email;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setStatus(StatusAluno status) {
        this.status = status != null ? status : this.status;
    }


    public void atualizarDados (
            String nome,
            LocalDate dataNascimento,
            Genero genero,
            String telefone,
            Email email,
            Endereco endereco,
            StatusAluno status
    ) {

        if (nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if (email == null) throw new EmailInvalidoException();

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.status = status != null ? status : this.status;

    }

    public void ativar() {
        this.status = StatusAluno.ATIVO;
    }

    public void desativar() {
        this.status = StatusAluno.INATIVO;
    }

    public boolean isAtivo() {
        return this.status == StatusAluno.ATIVO;
    }

    public void vincularPlano(PlanoId planoId) {
        if (planoId == null) {
            throw new IllegalArgumentException("Plano é obrigatório");
        }

        this.planoId = planoId;
    }

    public void removerPlano() { this.planoId = null; }

}
