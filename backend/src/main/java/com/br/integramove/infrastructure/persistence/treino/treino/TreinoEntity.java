package com.br.integramove.infrastructure.persistence.treino.treino;

import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.infrastructure.persistence.treino.item.TreinoItemEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treinos")
public class TreinoEntity {
    @Id
    private UUID id;
    private String nome;
    private String responsavel;
    private String funcionalidade;
    private String nivel;
    private String repeticoes;
    private String observacoes;
    @Enumerated(EnumType.STRING)
    private GrupoMuscular grupoMuscular;
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreinoItemEntity> exercicios = new ArrayList<>();
}
