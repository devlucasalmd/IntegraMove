package com.br.integramove.domain.aluno;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Endereco {

    private final String cep;
    private final String estado;
    private final String cidade;
    private final String rua;
    private final String numero;
    private final String bairro;

    public Endereco(String cep, String estado, String cidade, String rua, String numero, String bairro) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
}
