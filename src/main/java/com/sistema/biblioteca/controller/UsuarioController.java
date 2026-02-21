package com.sistema.biblioteca.controller;

import com.sistema.biblioteca.controller.dto.UsuarioDTO;
import com.sistema.biblioteca.controller.mappers.UsuarioMapper;
import com.sistema.biblioteca.model.Usuario;
import com.sistema.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final EmprestimoService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar (@RequestBody @Valid UsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);
        service.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable("id") String cpf){
        Optional<Usuario> usuarioOptional = service.obterUsuarioPorId(cpf);
        return service.obterUsuarioPorId(cpf).map(usuario -> {
            UsuarioDTO dto = mapper.toDTO(usuario);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String cpf){
        Optional<Usuario> usuarioOptional = service.obterUsuarioPorId(cpf);
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deletarUsuario(usuarioOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(
            @PathVariable("id") String cpf, @RequestBody @Valid UsuarioDTO dto){
        Optional<Usuario> usuarioOptional = service.obterUsuarioPorId(cpf);
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var usuario = usuarioOptional.get();
        usuario.setCpf(cpf);
        usuario.setNome(dto.nome());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setEndereco(dto.endereco());

        service.atualizarUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

}
