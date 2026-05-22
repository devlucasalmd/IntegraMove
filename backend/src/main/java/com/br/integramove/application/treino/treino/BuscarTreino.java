package com.br.integramove.application.treino.treino;

import com.br.integramove.api.exception.treino.treino.TreinoNaoEncontradoException;
import com.br.integramove.application.treino.item.TreinoItemOutput;
import com.br.integramove.domain.treino.treino.Treino;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTreino {

    private final TreinoRepository repository;

    public BuscarTreino(TreinoRepository repository) {
        this.repository = repository;
    }

    public BuscarTreinoOutput buscar(String id) {

        Treino treino = repository.buscarPorId(TreinoId.from(id))
                .orElseThrow(() -> new TreinoNaoEncontradoException("Treino não encontrado"));

        List<TreinoItemOutput> itens = treino.getExercicios()
                .stream()
                .map(item -> new TreinoItemOutput(
                        item.getId().getValue().toString(),
                        item.getExercicioId().getValue().toString(),
                        item.getSeries(),
                        item.getRepeticoes(),
                        item.getCarga(),
                        item.getDescanso(),
                        item.getOrdem()
                ))
                .toList();

        return new BuscarTreinoOutput(
                treino.getId().getValue().toString(),
                treino.getNome(),
                treino.getResponsavel(),
                treino.getFuncionalidade(),
                treino.getNivel(),
                treino.getRepeticoes(),
                treino.getObservacoes(),
                treino.getGrupoMuscular(),
                itens
        );
    }
}
