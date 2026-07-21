package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.UsuarioRequestDTO;
import com.br.integramove.api.dto.response.UsuarioResponseDTO;
import com.br.integramove.api.dto.response.UsuarioResumoResponseDTO;
import com.br.integramove.api.mapper.UsuarioMapper;
import com.br.integramove.application.usuario.inputs.AtualizarUsuarioInput;
import com.br.integramove.application.usuario.inputs.CriarUsuarioInput;
import com.br.integramove.application.usuario.inputs.DesativarUsuarioInput;
import com.br.integramove.application.usuario.outputs.AtualizarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.BuscarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.CriarUsuarioOutput;
import com.br.integramove.application.usuario.outputs.ListarUsuarioOutput;
import com.br.integramove.application.usuario.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;
    private final BuscarUsuario buscarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final AtualizarUsuario atualizarUsuario;
    private final DesativarUsuario desativarUsuario;

    public UsuarioController(
            CriarUsuario criarUsuario,
            BuscarUsuario buscarUsuario,
            ListarUsuarios listarUsuarios,
            AtualizarUsuario atualizarUsuario,
            DesativarUsuario desativarUsuario
    ) {
        this.criarUsuario = criarUsuario;
        this.buscarUsuario = buscarUsuario;
        this.listarUsuarios = listarUsuarios;
        this.atualizarUsuario = atualizarUsuario;
        this.desativarUsuario = desativarUsuario;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO request) {

        CriarUsuarioInput input = UsuarioMapper.toInput(request);
        CriarUsuarioOutput output = criarUsuario.criar(input);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UsuarioMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResumoResponseDTO>> listar() {

        List<ListarUsuarioOutput> output = listarUsuarios.listar();

        List<UsuarioResumoResponseDTO> response = output.stream()
                .map(UsuarioMapper::toResumoResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable String id) {

        BuscarUsuarioOutput output = buscarUsuario.buscar(id);

        return ResponseEntity.ok(UsuarioMapper.toResponse(output));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable String id,
            @RequestBody UsuarioRequestDTO request
    ) {

        AtualizarUsuarioInput input = UsuarioMapper.toAtualizar(id, request);
        AtualizarUsuarioOutput output = atualizarUsuario.atualizar(input);

        return ResponseEntity.ok(UsuarioMapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable String id) {

        desativarUsuario.desativar(new DesativarUsuarioInput(id));

        return ResponseEntity.noContent().build();
    }

}
