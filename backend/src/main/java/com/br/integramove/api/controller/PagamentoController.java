package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.PagamentoRequestDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.api.mapper.PagamentoMapper;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.BuscarPagamentoOutput;
import com.br.integramove.application.pagamento.outputs.CriarPagamentoOutput;
import com.br.integramove.application.pagamento.outputs.ListarPagamentosOutput;
import com.br.integramove.application.pagamento.services.BuscarPagamento;
import com.br.integramove.application.pagamento.services.CriarPagamento;
import com.br.integramove.application.pagamento.services.ListarPagamentos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final CriarPagamento criarPagamento;
    private final BuscarPagamento buscarPagamento;
    private final ListarPagamentos listarPagamentos;


    public PagamentoController(
            CriarPagamento criarPagamento,
            BuscarPagamento buscarPagamento,
            ListarPagamentos listarPagamentos
    ) {
        this.criarPagamento = criarPagamento;
        this.buscarPagamento = buscarPagamento;
        this.listarPagamentos = listarPagamentos;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criar(@RequestBody PagamentoRequestDTO dto){

        CriarPagamentoInput input = PagamentoMapper.toInput(dto);
        CriarPagamentoOutput output = criarPagamento.criar(input);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PagamentoMapper.toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscar(@PathVariable String id){
        BuscarPagamentoOutput output = buscarPagamento.buscar(id);
        return ResponseEntity.ok(PagamentoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listar(){

        List<ListarPagamentosOutput> outputs = listarPagamentos.listar();

        return ResponseEntity.ok(
                outputs.stream()
                        .map(PagamentoMapper::toResponse)
                        .toList()
        );
    }
}
