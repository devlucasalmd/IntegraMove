package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AtualizarDespesaRequestDTO;
import com.br.integramove.api.dto.request.DespesaRequestDTO;
import com.br.integramove.api.dto.request.PagarDespesaRequestDTO;
import com.br.integramove.api.dto.response.DespesaResponseDTO;
import com.br.integramove.api.mapper.DespesaMapper;
import com.br.integramove.application.despesa.outputs.DespesaOutput;
import com.br.integramove.application.despesa.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private final CriarDespesa criarDespesa;
    private final ListarDespesas listarDespesas;
    private final BuscarDespesa buscarDespesa;
    private final AtualizarDespesa atualizarDespesa;
    private final PagarDespesa pagarDespesa;
    private final CancelarDespesa cancelarDespesa;
    private final DespesaMapper despesaMapper;

    public DespesaController(CriarDespesa criarDespesa, ListarDespesas listarDespesas, BuscarDespesa buscarDespesa, AtualizarDespesa atualizarDespesa, PagarDespesa pagarDespesa, CancelarDespesa cancelarDespesa, DespesaMapper despesaMapper) {
        this.criarDespesa = criarDespesa;
        this.listarDespesas = listarDespesas;
        this.buscarDespesa = buscarDespesa;
        this.atualizarDespesa = atualizarDespesa;
        this.pagarDespesa = pagarDespesa;
        this.cancelarDespesa = cancelarDespesa;
        this.despesaMapper = despesaMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DespesaResponseDTO criar(@RequestBody DespesaRequestDTO request) {
        DespesaOutput output = criarDespesa.executar(
                despesaMapper.toInput(request)
        );

        return despesaMapper.toResponse(output);
    }

    @GetMapping
    public List<DespesaResponseDTO> listar() {
        return listarDespesas.executar()
                .stream()
                .map(despesaMapper::toResponse)
                .toList();
    }

    @GetMapping("/{despesaId}")
    public DespesaResponseDTO buscarPorId(@PathVariable String despesaId) {
        DespesaOutput output = buscarDespesa.executar(despesaId);
        return despesaMapper.toResponse(output);
    }

    @PutMapping("/{despesaId}")
    public DespesaResponseDTO atualizar(
            @PathVariable String despesaId,
            @RequestBody AtualizarDespesaRequestDTO request
    ) {
        DespesaOutput output = atualizarDespesa.executar(
                despesaId,
                despesaMapper.toInput(request)
        );

        return despesaMapper.toResponse(output);
    }

    @PatchMapping("/{despesaId}/pagar")
    public DespesaResponseDTO pagar(
            @PathVariable String despesaId,
            @RequestBody PagarDespesaRequestDTO request
    ) {
        DespesaOutput output = pagarDespesa.executar(
                despesaId,
                despesaMapper.toInput(request)
        );

        return despesaMapper.toResponse(output);
    }

    @PatchMapping("/{despesaId}/cancelar")
    public DespesaResponseDTO cancelar(@PathVariable String despesaId) {
        DespesaOutput output = cancelarDespesa.executar(despesaId);
        return despesaMapper.toResponse(output);
    }
}
