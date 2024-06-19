package com.example.security.controllers;

import com.example.security.models.Tecido;
import com.example.security.service.TecidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecido")

public class TecidoController {

    @Autowired
    private TecidoService tecidoService;

    //Listar todos os tecidos
    @GetMapping("/")
    public ResponseEntity<List<Tecido>> listarUsuariosPorId() {
        List<Tecido> tecidos = tecidoService.listarTodosTecidos();
        return ResponseEntity.ok(tecidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecido> obterTecidoPorId(@PathVariable Long id) {
        Tecido tecido = tecidoService.obterTecidoPorId(id);
        if (tecido != null) {
            return ResponseEntity.ok(tecido);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Tecido> adicionarTecido(@RequestBody Tecido tecido){
        Tecido novoTecido = tecidoService.adicionarTecido(tecido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTecido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecido> atualizarTecido(@PathVariable Long id, @RequestBody Tecido tecidoAtualizado){
        Tecido tecido = tecidoService.obterTecidoPorId(id);
        if (tecido != null){
            tecidoAtualizado.setId(id);
            tecidoAtualizado = tecidoService.atualizarTecido(tecidoAtualizado);
            return ResponseEntity.ok(tecidoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTecido(@PathVariable Long id) {
        Tecido tecido = tecidoService.obterTecidoPorId(id);
        if (tecido != null){
            tecidoService.excluirTecido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
