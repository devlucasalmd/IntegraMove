package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.ExercicioRequestDTO;
import com.br.integramove.api.dto.response.ExercicioResponseDTO;
import com.br.integramove.api.mapper.ExercicioMapper;
import com.br.integramove.application.treino.exercicio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final CriarExercicio criarExercicio;
    private final ListarExercicios listarExercicios;
    private final BuscarExercicio buscarExercicio;
    private final AtualizarExercicio atualizarExercicio;
    private final ExcluirExercicio excluirExercicio;
    private final ExcluirExercicioFisico excluirExercicioFisico;


    public ExercicioController(
            CriarExercicio criarExercicio,
            ListarExercicios listarExercicios,
            BuscarExercicio buscarExercicio,
            AtualizarExercicio atualizarExercicio,
            ExcluirExercicio excluirExercicio,
            ExcluirExercicioFisico excluirExercicioFisico
    ) {
        this.criarExercicio = criarExercicio;
        this.listarExercicios = listarExercicios;
        this.buscarExercicio = buscarExercicio;
        this.atualizarExercicio = atualizarExercicio;
        this.excluirExercicio = excluirExercicio;
        this.excluirExercicioFisico = excluirExercicioFisico;
    }

    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> criar(@RequestBody ExercicioRequestDTO request) {

        CriarExercicioInput input = ExercicioMapper.toInput(request);

        CriarExercicioOutput output = criarExercicio.criar(input);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ExercicioMapper.toResponse(output));

    }

    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> listar() {

        List<ListarExercicioOutput> outputs = listarExercicios.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(ExercicioMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> buscar(@PathVariable String id) {

        BuscarExercicioOutput output = buscarExercicio.buscar(id);

        return ResponseEntity.ok(ExercicioMapper.toResponse(output));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizar(@PathVariable String id, @RequestBody ExercicioRequestDTO request) {
        AtualizarExercicioInput input = ExercicioMapper.toAtualizar(id, request);

        AtualizarExercicioOutput output = atualizarExercicio.atualizar(input);

        return ResponseEntity.ok(ExercicioMapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {

        excluirExercicio.excluir(new ExcluirExercicioInput(id));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> excluirFisico(@PathVariable String id) {

        excluirExercicioFisico.excluir(id);

        return ResponseEntity.noContent().build();
    }


}
