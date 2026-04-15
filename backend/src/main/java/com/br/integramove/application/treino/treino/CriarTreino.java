package com.br.integramove.application.treino.treino;

import com.br.integramove.application.treino.exercicio.ExercicioRepository;
import com.br.integramove.application.treino.item.TreinoItemOutput;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import com.br.integramove.domain.treino.treino.Treino;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriarTreino {

    private final TreinoRepository repository;
    private final ExercicioRepository exercicioRepository;

    public CriarTreino(TreinoRepository repository, ExercicioRepository exercicioRepository) {
        this.repository = repository;
        this.exercicioRepository = exercicioRepository;
    }

    public CriarTreinoOutput criar(CriarTreinoInput input) {

        Treino treino = new Treino(
                TreinoId.novo(),
                input.nome(),
                input.responsavel(),
                input.funcionalidade(),
                input.nivel(),
                input.repeticoes()
        );

        List<TreinoItem> itens = input.exercicios()
                .stream()
                .map(item -> new TreinoItem(
                        TreinoItemId.novo(),
                        ExercicioId.from(item.exercicioId()),
                        item.series(),
                        item.repeticoes(),
                        item.carga(),
                        item.descanso(),
                        item.ordem()
                ))
                .toList();

        treino.adicionarExercicios(itens);

        repository.salvar(treino);

        List<TreinoItemOutput> itensOutput = itens.stream()
                .map(item -> {
                    String nomeExercicio = exercicioRepository
                            .buscarPorId(item.getExercicioId())
                            .get().getNome();


                    return new TreinoItemOutput(
                            item.getExercicioId().toString(),
                            nomeExercicio,
                            item.getSeries(),
                            item.getRepeticoes(),
                            item.getCarga(),
                            item.getDescanso(),
                            item.getOrdem()
                    );
                })
                .toList();

        return new CriarTreinoOutput(
                treino.getId().getValue().toString(),
                treino.getNome(),
                treino.getResponsavel(),
                treino.getFuncionalidade(),
                treino.getNivel(),
                treino.getRepeticoes(),
                itensOutput
        );
    }
}
