package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.ContratoRequestDTO;
import com.br.integramove.api.dto.response.ContratoResponseDTO;
import com.br.integramove.api.mapper.ContratoMapper;
import com.br.integramove.application.contrato.inputs.CriarContratoInput;
import com.br.integramove.application.contrato.outputs.ContratoOutput;
import com.br.integramove.application.contrato.services.BuscarContrato;
import com.br.integramove.application.contrato.services.CriarContrato;
import com.br.integramove.application.contrato.services.ListarContratosPorAluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final CriarContrato criarContrato;
    private final BuscarContrato buscarContrato;
    private final ListarContratosPorAluno listarContratosPorAluno;

    public ContratoController(
            CriarContrato criarContrato,
            BuscarContrato buscarContrato,
            ListarContratosPorAluno listarContratosPorAluno
    ) {
        this.criarContrato = criarContrato;
        this.buscarContrato = buscarContrato;
        this.listarContratosPorAluno = listarContratosPorAluno;
    }

    @PostMapping
    public ResponseEntity<ContratoResponseDTO> criar(@RequestBody ContratoRequestDTO dto) {
        CriarContratoInput input = ContratoMapper.toInput(dto);
        ContratoOutput output = criarContrato.criar(input);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ContratoMapper.toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> buscar(@PathVariable String id) {
        ContratoOutput output = buscarContrato.buscar(id);
        return ResponseEntity.ok(ContratoMapper.toResponse(output));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<ContratoResponseDTO>> listarPorAluno(@PathVariable String alunoId) {
        List<ContratoOutput> outputs = listarContratosPorAluno.listar(alunoId);
        return ResponseEntity.ok(
                outputs.stream()
                        .map(ContratoMapper::toResponse)
                        .toList()
        );
    }
}