package com.br.integramove.domain.usuario;

import com.br.integramove.domain.enums.PerfilUsuario;
import com.br.integramove.domain.valueobjects.Cpf;
import com.br.integramove.domain.valueobjects.Email;

import java.util.UUID;

public class Usuario {

    private UsuarioId id;
    private String nome;
    private Cpf cpf;
    private String telefone;
    private Email email;
    private String senha;
    private PerfilUsuario perfil;
    private Boolean ativo;

    public Usuario(
            UsuarioId id,
            String nome,
            Cpf cpf,
            String telefone,
            Email email,
            String senha,
            PerfilUsuario perfil,
            Boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public UsuarioId getId() {
        return id;
    }

    public void setId(UsuarioId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void atualizarDados(
            String nome,
            String telefone,
            Email email,
            PerfilUsuario perfil,
            Boolean ativo
    ) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public void desativar() {
        this.ativo = false;
    }
}
