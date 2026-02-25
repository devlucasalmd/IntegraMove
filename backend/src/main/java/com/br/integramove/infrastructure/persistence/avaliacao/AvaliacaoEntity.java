package com.br.integramove.infrastructure.persistence.avaliacao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avaliacoes")
public class AvaliacaoEntity {

    @Id
    private Long id;
    private LocalDate dataAvaliacao;
    private Double peso;
    private Double altura;
    private Double imc;
    private Double percentualGordura;
    private Double circuferencia;

}
