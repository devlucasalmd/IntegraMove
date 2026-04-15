package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.TreinoItemRequestDTO;
import com.br.integramove.api.dto.response.TreinoItemResponseDTO;
import com.br.integramove.api.mapper.TreinoItemMapper;
import com.br.integramove.application.treino.item.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treino/item")
public class TreinoItemController {

    private final CriarTreinoItem criarTreinoItem;
    private final BuscarTreinoItem buscarTreinoItem;
    private final ListarTreinoItem listarTreinoItem;
    private final AtualizarTreinoItem atualizarTreinoItem;


    public TreinoItemController(
            CriarTreinoItem criarTreinoItem,
            BuscarTreinoItem buscarTreinoItem,
            ListarTreinoItem listarTreinoItem,
            AtualizarTreinoItem atualizarTreinoItem
    ) {
        this.criarTreinoItem = criarTreinoItem;
        this.buscarTreinoItem = buscarTreinoItem;
        this.listarTreinoItem = listarTreinoItem;
        this.atualizarTreinoItem = atualizarTreinoItem;
    }


    @PostMapping
    public ResponseEntity<TreinoItemResponseDTO> criar(@RequestBody TreinoItemRequestDTO request){

        CriarTreinoItemInput input = TreinoItemMapper.toInput(request);

        CriarTreinoItemOutput output = criarTreinoItem.criar(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(TreinoItemMapper.toResponse(output));

    }

    @GetMapping
    public ResponseEntity<List<TreinoItemResponseDTO>> listar(){

        List<ListarTreinoItemOutput> outputs = listarTreinoItem.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(TreinoItemMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoItemResponseDTO> buscar(@PathVariable String id){

        BuscarTreinoItemOutput output = buscarTreinoItem.buscar(id);

        return ResponseEntity.ok(TreinoItemMapper.toResponse(output));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoItemResponseDTO> atualizar(@PathVariable String id, @RequestBody TreinoItemRequestDTO request){

        AtualizarTreinoItemInput input = TreinoItemMapper.toAtualizar(id, request);

        AtualizarTreinoItemOutput output = atualizarTreinoItem.atualizar(input);

        return ResponseEntity.ok(TreinoItemMapper.toResponse(output));
    }



}
