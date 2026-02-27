package com.br.integramove.domain.aluno;

import com.br.integramove.api.exception.aluno.CpfInvalidoException;
import com.br.integramove.api.exception.aluno.EmailInvalidoException;
import com.br.integramove.api.exception.aluno.NomeInvalidoException;

import java.time.LocalDate;

public class Aluno {

    private final AlunoId id;
    private String nome;
    private LocalDate dataNascimento;
    private Cpf cpf;
    private Genero genero;
    private String telefone;
    private Email email;
    private Endereco endereco;
    private boolean ativo = true;

    public Aluno(
            AlunoId id,
            String nome,
            LocalDate dataNascimento,
            Cpf cpf,
            Genero genero,
            String telefone,
            Email email,
            Endereco endereco,
            Boolean ativo
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
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public void ativar(){
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public void atualizarDados (
            String nome,
            LocalDate dataNascimento,
            Genero genero,
            String telefone,
            Email email,
            Endereco endereco,
            Boolean ativo
    ) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public boolean estaAtivo(){
        return ativo;
    }

    public AlunoId getId() { return id; }

    public String getNome() { return nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public Cpf getCpf() { return cpf; }

    public Genero getGenero() { return genero; }

    public String getTelefone() { return telefone; }

    public Email getEmail() { return email; }

    public Endereco getEndereco() { return endereco; }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
