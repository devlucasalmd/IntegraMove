package com.br.integramove.application.plano;

import com.br.integramove.application.plano.outputs.ListarPlanosOutput;
import com.br.integramove.domain.plano.Plano;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPlanos {

    private final PlanoRepository planoRepository;


    public ListarPlanos(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<ListarPlanosOutput> listar(){

        List<Plano> planos = planoRepository.buscarTodosPlanos();

        return planos.stream()
                .map(plano -> new ListarPlanosOutput(
                        plano.getId().getValue().toString(),
                        plano.getNome(),
                        plano.getValor(),
                        plano.getDescricao(),
                        plano.getAtivo()
                ))
                .toList();
    }
}
