package com.example.security.controllers;

import com.example.security.models.usuario.Usuario;
import com.example.security.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
@Tag(name = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuario();
        return ResponseEntity.ok(usuarios);
    }

    // Obter um usuario pelo ID
    @Operation(summary = "Busca os dados do usuario pelo ID exercido", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar uma camisa existente
    @Operation(summary = "Realiza o upload de arquivos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload de arquivos realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Atualizar uma camisa existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario != null) {
            usuarioAtualizado.setId(id);
            usuarioAtualizado = usuarioService.atualizarUsuario(usuarioAtualizado);
            return ResponseEntity.ok(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir uma camisa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario != null) {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
