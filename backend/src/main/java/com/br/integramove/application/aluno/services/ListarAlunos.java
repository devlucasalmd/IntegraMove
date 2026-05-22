package com.br.integramove.application.aluno.services;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.ListarAlunosOutput;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.StatusAluno;
import com.br.integramove.domain.pagamento.StatusPagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunos {

    private final AlunoRepository repository;

    public ListarAlunos(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<ListarAlunosOutput> listar() {
        return repository.listarTodos()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private ListarAlunosOutput toOutput(Aluno aluno) {
        return new ListarAlunosOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),

                // Temporário até integrar com Plano
                "Sem plano",

                // Temporário até integrar com Financeiro
                StatusPagamento.EM_ABERTO,

                aluno.getStatus()
        );
    }
}
