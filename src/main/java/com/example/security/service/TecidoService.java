package com.example.security.service;


import com.example.security.models.Tecido;
import com.example.security.repository.TecidoRepositorio;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecidoService {

    @Autowired
    TecidoRepositorio tecidoRepositorio;

    //Listar todos os tecidos
    public List<Tecido> listarTodosTecidos() {
        return tecidoRepositorio.findAll();
    }

    //Buscar tecido pelo ID
    public Tecido obterTecidoPorId(Long id) {
        Optional<Tecido> tecidoOptional = tecidoRepositorio.findById(id);
        return tecidoOptional.orElse(null);
    }

    //Adicionar um novo tecido
    public Tecido adicionarTecido(Tecido tecido) {
        return tecidoRepositorio.save(tecido);
    }

    //Editar um tecido existente
    public Tecido atualizarTecido(Tecido tecido) {
        return tecidoRepositorio.save(tecido);
    }

    //Excluir um tecido pelo ID
    public void excluirTecido(Long id) {
        tecidoRepositorio.deleteById(id);
    }

}
