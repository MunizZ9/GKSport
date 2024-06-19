package com.example.security.controllers;


import com.example.security.models.Orcamento;
import com.example.security.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    // Listar todas as orcamentos
    @GetMapping("/")
    public ResponseEntity<List<Orcamento>> listarOrcamento() {
        List<Orcamento> orcamentos = orcamentoService.listarTodosOrcamentos();
        return ResponseEntity.ok(orcamentos);
    }

    // Obter uma orcamento pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> obterOrcamentoPorId(@PathVariable("id") Long id) {
        Orcamento orcamento = orcamentoService.obterOrcamentoPorId(id);
        if (orcamento != null) {
            return ResponseEntity.ok(orcamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar uma nova orcamento
    @PostMapping("/")
    public ResponseEntity<Orcamento> adicionarOrcamento(@RequestBody Orcamento orcamento) {
        Orcamento novaOrcamento = orcamentoService.adicionarOrcamento(orcamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOrcamento);
    }

    // Atualizar uma orcamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> atualizarOrcamento(@PathVariable("id") Long id, @RequestBody Orcamento orcamentoAtualizado) {
        Orcamento orcamento = orcamentoService.obterOrcamentoPorId(id);
        if (orcamento != null) {
            orcamentoAtualizado.setId(id);
            orcamentoAtualizado = orcamentoService.atualizarOrcamento(orcamentoAtualizado);
            return ResponseEntity.ok(orcamentoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir uma orcamento pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirOrcamento(@PathVariable("id") Long id) {
        Orcamento orcamento = orcamentoService.obterOrcamentoPorId(id);
        if (orcamento != null) {
            orcamentoService.excluirOrcamento(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
