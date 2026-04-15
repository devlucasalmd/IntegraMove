package com.br.integramove.api.controller;


import com.br.integramove.api.dto.request.TreinoAlunoRequestDTO;
import com.br.integramove.api.dto.response.TreinoAlunoResponseDTO;
import com.br.integramove.api.dto.response.TreinoResponseDTO;
import com.br.integramove.api.mapper.TreinoAlunoMapper;
import com.br.integramove.application.treino.aluno.*;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treino-aluno")
public class TreinoAlunoController {

    private final CriarTreinoAluno criarTreinoAluno;
    private final BuscarTreinoAluno buscarTreinoAluno;
    private final ListarTreinosAluno listarTreinosAluno;


    public TreinoAlunoController(
            CriarTreinoAluno criarTreinoAluno,
            BuscarTreinoAluno buscarTreinoAluno,
            ListarTreinosAluno listarTreinosAluno
    ) {
        this.criarTreinoAluno = criarTreinoAluno;
        this.buscarTreinoAluno = buscarTreinoAluno;
        this.listarTreinosAluno = listarTreinosAluno;
    }

    @PostMapping
    public ResponseEntity<TreinoAlunoResponseDTO> criar(@RequestBody TreinoAlunoRequestDTO request){

        CriarTreinoAlunoInput input = TreinoAlunoMapper.toInput(request);

        CriarTreinoAlunoOutput output = criarTreinoAluno.criar(input);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TreinoAlunoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<TreinoAlunoResponseDTO>> listar(){

        List<ListarTreinoAlunoOutput> outputs = listarTreinosAluno.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(TreinoAlunoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoAlunoResponseDTO> buscar(@PathVariable String id){

        BuscarTreinoAlunoOutput output = buscarTreinoAluno.buscar(id);

        return ResponseEntity.ok(TreinoAlunoMapper.toResponse(output));
    }
}
