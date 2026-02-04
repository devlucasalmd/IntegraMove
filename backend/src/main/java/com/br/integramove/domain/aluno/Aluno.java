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

    public Aluno(AlunoId id, String nome, LocalDate dataNascimento, Cpf cpf, Genero genero, String telefone, Email email, Endereco endereco) {

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

    }

    public void ativar(){
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public void atualizarDados( String nome, LocalDate dataNascimento, Genero genero, String telefone, Email email, Endereco endereco) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;

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

    public boolean isAtivo() { return ativo; }

}
