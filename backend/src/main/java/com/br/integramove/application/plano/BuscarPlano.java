package com.br.integramove.application.plano;

import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarPlano {

    private final PlanoRepository planoRepository;


    public BuscarPlano(PlanoRepository planoRepository) { this.planoRepository = planoRepository; }

    public BuscarPlanoOutput buscar(String planoId){

        PlanoId id = PlanoId.from(planoId);

        Plano plano = planoRepository.buscarPorId(id).orElseThrow();

        return new BuscarPlanoOutput(
                plano.getId().getValue().toString(),
                plano.getNome(),
                plano.getValor(),
                plano.getDescricao(),
                plano.getAtivo()
        );
    }
}
