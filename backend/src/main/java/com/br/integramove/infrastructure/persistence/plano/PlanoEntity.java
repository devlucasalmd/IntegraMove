package com.br.integramove.infrastructure.persistence.plano;

import com.br.integramove.domain.plano.PlanoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "planos")
public class PlanoEntity {

    @Id
    private UUID id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Boolean ativo;

}
