package com.example.security.controllers;

import com.example.security.models.Camisa;
import com.example.security.service.CamisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/camisa")
public class CamisaController {

    @Autowired
    private CamisaService camisaService;

    // Listar todas as camisas
    @GetMapping("/")
    public ResponseEntity<List<Camisa>> listarCamisas() {
        List<Camisa> camisas = camisaService.listarTodasCamisa();
        return ResponseEntity.ok(camisas);
    }

    // Obter uma camisa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Camisa> obterCamisaPorId(@PathVariable("id") Long id) {
        Camisa camisa = camisaService.obterCamisaPorId(id);
        if (camisa != null) {
            return ResponseEntity.ok(camisa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar uma nova camisa
    @PostMapping("/")
    public ResponseEntity<Camisa> adicionarCamisa(@RequestBody Camisa camisa) {
        Camisa novaCamisa = camisaService.adicionarCamisa(camisa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCamisa);
    }

    // Atualizar uma camisa existente
    @PutMapping("/{id}")
    public ResponseEntity<Camisa> atualizarCamisa(@PathVariable("id") Long id, @RequestBody Camisa camisaAtualizada) {
        Camisa camisa = camisaService.obterCamisaPorId(id);
        if (camisa != null) {
            camisaAtualizada.setId(id);
            camisaAtualizada = camisaService.atualizarCamisa(camisaAtualizada);
            return ResponseEntity.ok(camisaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir uma camisa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCamisa(@PathVariable("id") Long id) {
        Camisa camisa = camisaService.obterCamisaPorId(id);
        if (camisa != null) {
            camisaService.excluirCamisa(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}