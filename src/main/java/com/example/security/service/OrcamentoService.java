package com.example.security.service;

import com.example.security.models.Orcamento;
import com.example.security.repository.OrcamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class OrcamentoService {

        @Autowired
        OrcamentoRepositorio orcamentoRepositorio;

        //Listar todos os Orcamentos
        public List<Orcamento> listarTodosOrcamentos() {
            return orcamentoRepositorio.findAll();
        }

        //Buscar orcamento pelo ID
        public Orcamento obterOrcamentoPorId(Long id) {
            Optional<Orcamento> orcamentoOptional = orcamentoRepositorio.findById(id);
            return orcamentoOptional.orElse(null);
        }

        //Adicionar um novo orcamento
        public Orcamento adicionarOrcamento(Orcamento orcamento) {
            return orcamentoRepositorio.save(orcamento);
        }

        //Editar um orcamento existente
        public Orcamento atualizarOrcamento(Orcamento orcamento) {
            return orcamentoRepositorio.save(orcamento);
        }

        //Excluir um orcamento pelo ID
        public void excluirOrcamento(Long id) {
            orcamentoRepositorio.deleteById(id);
        }


}
