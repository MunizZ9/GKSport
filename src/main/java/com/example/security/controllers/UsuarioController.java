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
    @Operation(summary = "Busca os dados de todos os usuarios", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
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
            @ApiResponse(responseCode = "404", description = "Não encontrado")
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

    // Adicionar um usuario
    @Operation(summary = "Adiona um usuario", method = "POST")
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

    // Atualizar um usuario existente
    @Operation(summary = "Atualiza um usuario existente pelo ID exercido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
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

    // Excluir um usuario pelo ID
    @Operation(summary = "Deleta um usuario pelo ID exercido", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
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