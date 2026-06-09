package com.br.integramove.api.controller;


import com.br.integramove.api.dto.request.TreinoAlunoRequestDTO;
import com.br.integramove.api.dto.response.TreinoAlunoResponseDTO;
import com.br.integramove.api.mapper.TreinoAlunoMapper;
import com.br.integramove.application.treino.aluno.inputs.CriarTreinoAlunoInput;
import com.br.integramove.application.treino.aluno.outputs.BuscarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.outputs.CriarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.outputs.ListarTreinoAlunoOutput;
import com.br.integramove.application.treino.aluno.services.BuscarTreinoAluno;
import com.br.integramove.application.treino.aluno.services.CriarTreinoAluno;
import com.br.integramove.application.treino.aluno.services.ListarTreinosAluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alunos/{alunoId}/treinos")
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
    public ResponseEntity<TreinoAlunoResponseDTO> criar(
            @PathVariable String alunoId,
            @RequestBody TreinoAlunoRequestDTO request
    ) {
        CriarTreinoAlunoInput input = TreinoAlunoMapper.toInput(alunoId, request);

        CriarTreinoAlunoOutput output = criarTreinoAluno.criar(input);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TreinoAlunoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<TreinoAlunoResponseDTO>> listar(
            @PathVariable String alunoId
    ) {
        List<ListarTreinoAlunoOutput> outputs = listarTreinosAluno.listar(alunoId);

        return ResponseEntity.ok(
                outputs.stream()
                        .map(TreinoAlunoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoAlunoResponseDTO> buscar(
            @PathVariable String alunoId,
            @PathVariable String id
    ) {
        BuscarTreinoAlunoOutput output = buscarTreinoAluno.buscar(alunoId, id);

        return ResponseEntity.ok(TreinoAlunoMapper.toResponse(output));
    }
}