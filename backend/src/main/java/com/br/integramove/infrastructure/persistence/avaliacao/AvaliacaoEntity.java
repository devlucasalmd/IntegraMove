package com.br.integramove.infrastructure.persistence.avaliacao;

import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.infrastructure.persistence.aluno.AlunoEntity;
import jakarta.persistence.*;
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
@Table(name = "avaliacao")
public class AvaliacaoEntity {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;
    private LocalDate dataAvaliacao;
    @Column(name = "remada_braco_d")
    private BigDecimal remadaBracoD;
    @Column(name = "remada_braco_e")
    private BigDecimal remadaBracoE;
    @Column(name = "elevacao_lat_d")
    private BigDecimal elevacaoLatD;
    @Column(name = "elevacao_lat_e")
    private BigDecimal elevacaoLatE;
    @Column(name = "extensao_joelho_d")
    private BigDecimal extensaoJoelhoD;
    @Column(name = "extensao_joelho_e")
    private BigDecimal extensaoJoelhoE;
    @Column(name = "flexao_joelho_d")
    private BigDecimal flexaoJoelhoD;
    @Column(name = "flexao_joelho_e")
    private BigDecimal flexaoJoelhoE;
    @Column(name = "extensao_quadril_d")
    private BigDecimal extensaoQuadrilD;
    @Column(name = "extensao_quadril_e")
    private BigDecimal extensaoQuadrilE;

}
