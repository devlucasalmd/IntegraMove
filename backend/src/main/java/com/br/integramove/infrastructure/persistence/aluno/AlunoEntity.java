package com.br.integramove.infrastructure.persistence.aluno;

import com.br.integramove.domain.enums.StatusAluno;
import com.br.integramove.infrastructure.persistence.avaliacao.AvaliacaoEntity;
import com.br.integramove.infrastructure.persistence.plano.PlanoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
public class AlunoEntity {
    @Id
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String genero;
    private String telefone;
    private String email;
    private StatusAluno status;
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String bairro;
    @OneToMany(mappedBy = "aluno")
    private List<AvaliacaoEntity> avaliacoes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;
}
