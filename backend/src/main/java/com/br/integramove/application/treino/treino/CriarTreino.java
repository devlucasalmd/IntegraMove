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
                input.repeticoes(),
                input.observacoes(),
                input.grupoMuscular()
        );

        repository.salvar(treino);

        return new CriarTreinoOutput(
                treino.getId().getValue().toString(),
                treino.getNome(),
                treino.getResponsavel(),
                treino.getFuncionalidade(),
                treino.getNivel(),
                treino.getRepeticoes(),
                treino.getObservacoes(),
                treino.getGrupoMuscular()
        );
    }
}
