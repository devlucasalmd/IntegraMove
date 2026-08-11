package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.PlanoRequestDTO;
import com.br.integramove.api.dto.response.PlanoResponseDTO;
import com.br.integramove.api.mapper.PlanoMapper;
import com.br.integramove.application.plano.inputs.AtualizarPlanoInput;
import com.br.integramove.application.plano.inputs.CriarPlanoInput;
import com.br.integramove.application.plano.outputs.AtualizarPlanoOutput;
import com.br.integramove.application.plano.outputs.BuscarPlanoOutput;
import com.br.integramove.application.plano.outputs.CriarPlanoOutput;
import com.br.integramove.application.plano.outputs.ListarPlanosOutput;
import com.br.integramove.application.plano.services.AtualizarPlano;
import com.br.integramove.application.plano.services.BuscarPlano;
import com.br.integramove.application.plano.services.CriarPlano;
import com.br.integramove.application.plano.services.ListarPlanos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final CriarPlano criarPlano;
    private final BuscarPlano buscarPlano;
    private final ListarPlanos listarPlanos;
    private final AtualizarPlano atualizarPlano;


    public PlanoController(
            CriarPlano criarPlano,
            BuscarPlano buscarPlano,
            ListarPlanos listarPlanos,
            AtualizarPlano atualizarPlano
    ) {
        this.criarPlano = criarPlano;
        this.buscarPlano = buscarPlano;
        this.listarPlanos = listarPlanos;
        this.atualizarPlano = atualizarPlano;
    }

    @PostMapping
    public ResponseEntity<PlanoResponseDTO> criar(@RequestBody PlanoRequestDTO dto){
        CriarPlanoInput input = PlanoMapper.toInput(dto);
        CriarPlanoOutput output = criarPlano.criar(input);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PlanoMapper.toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable String id){
        BuscarPlanoOutput output = buscarPlano.buscar(id);
        return ResponseEntity.ok(PlanoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> listar() {

        List<ListarPlanosOutput> outputs = listarPlanos.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(PlanoMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody PlanoRequestDTO dto){

        AtualizarPlanoInput input = PlanoMapper.toAtualizar(id, dto);

        AtualizarPlanoOutput output = atualizarPlano.atualizar(input);

        return ResponseEntity.ok(PlanoMapper.toResponse(output));
    }

}
