package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.VendaRequestDTO;
import com.br.integramove.api.dto.response.VendaResponseDTO;
import com.br.integramove.api.mapper.VendaMapper;
import com.br.integramove.application.venda.inputs.CriarVendaInput;
import com.br.integramove.application.venda.outputs.VendaOutput;
import com.br.integramove.application.venda.services.BuscarVenda;
import com.br.integramove.application.venda.services.CriarVenda;
import com.br.integramove.application.venda.services.ListarVendasPorAluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final CriarVenda criarVenda;
    private final BuscarVenda buscarVenda;
    private final ListarVendasPorAluno listarVendasPorAluno;

    public VendaController(
            CriarVenda criarVenda,
            BuscarVenda buscarVenda,
            ListarVendasPorAluno listarVendasPorAluno
    ) {
        this.criarVenda = criarVenda;
        this.buscarVenda = buscarVenda;
        this.listarVendasPorAluno = listarVendasPorAluno;
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@RequestBody VendaRequestDTO dto) {
        CriarVendaInput input = VendaMapper.toInput(dto);
        VendaOutput output = criarVenda.criar(input);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(VendaMapper.toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscar(@PathVariable String id) {
        VendaOutput output = buscarVenda.buscar(id);
        return ResponseEntity.ok(VendaMapper.toResponse(output));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<VendaResponseDTO>> listarPorAluno(@PathVariable String alunoId) {
        List<VendaOutput> outputs = listarVendasPorAluno.listar(alunoId);
        return ResponseEntity.ok(
                outputs.stream()
                        .map(VendaMapper::toResponse)
                        .toList()
        );
    }
}