package com.br.integramove.infrastructure.persistence.pagamento;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    private UUID id;
    private BigDecimal valor;
    private LocalDate data;
    private FormaPagamento formaPagamento;
    private String status;

}
