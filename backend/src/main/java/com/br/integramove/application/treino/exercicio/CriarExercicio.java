package com.br.integramove.application.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.exercicio.GrupoMuscular;
import org.springframework.stereotype.Service;

@Service
public class CriarExercicio {

    private final ExercicioRepository repository;

    public CriarExercicio(ExercicioRepository repository){
        this.repository = repository;
    }

    public CriarExercicioOutput criar(CriarExercicioInput exercicioInput){

        Exercicio exercicio = new Exercicio(
                ExercicioId.novo(),
                exercicioInput.nome(),
                exercicioInput.grupoMuscular(),
                exercicioInput.descricao(),
                exercicioInput.intensidade(),
                exercicioInput.ativo()
        );

        Exercicio exercicioSalvo = repository.salvar(exercicio);

        return new CriarExercicioOutput(
                exercicioSalvo.getId().getValue().toString(),
                exercicioSalvo.getNome(),
                exercicioSalvo.getGrupoMuscular(),
                exercicioSalvo.getDescricao(),
                exercicioSalvo.getIntensidade(),
                exercicioSalvo.getAtivo()
        );
    }
}
