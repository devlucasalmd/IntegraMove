package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.TreinoRequestDTO;
import com.br.integramove.api.dto.response.TreinoResponseDTO;
import com.br.integramove.api.mapper.TreinoMapper;
import com.br.integramove.application.treino.treino.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final CriarTreino criarTreino;
    private final BuscarTreino buscarTreino;
    private final ListarTreinos listarTreinos;


    public TreinoController(
            CriarTreino criarTreino,
            BuscarTreino buscarTreino,
            ListarTreinos listarTreinos
    ) {
        this.criarTreino = criarTreino;
        this.buscarTreino = buscarTreino;
        this.listarTreinos = listarTreinos;
    }

    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criar(@RequestBody TreinoRequestDTO request){

        CriarTreinoInput input = TreinoMapper.toInput(request);

        CriarTreinoOutput output = criarTreino.criar(input);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TreinoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> listar(){

        List<ListarTreinosOutput> outputs = listarTreinos.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(TreinoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> buscar(@PathVariable String id){

        BuscarTreinoOutput output = buscarTreino.buscar(id);

        return ResponseEntity.ok(TreinoMapper.toResponse(output));
    }
}
