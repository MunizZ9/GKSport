package com.example.security.service;

import com.example.security.models.Camisa;
import com.example.security.repository.CamisaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CamisaService {

    @Autowired
    private CamisaRepositorio camisaRepositorio;

    // Listar todas as camisas
    public List<Camisa> listarTodasCamisa() {
        return camisaRepositorio.findAll();
    }

    // Obter uma criança pelo ID
    public Camisa obterCamisaPorId(Long id) {
        Optional<Camisa> optionalCamisa = camisaRepositorio.findById(id);
        return optionalCamisa.orElse(null);
    }

    // Adicionar uma nova criança
    public Camisa adicionarCamisa(Camisa camisa) {
        return camisaRepositorio.save(camisa);
    }

    // Atualizar uma criança existente
    public Camisa atualizarCamisa(Camisa camisa) {
        return camisaRepositorio.save(camisa);
    }

    // Excluir uma criança pelo ID
    public void excluirCamisa(Long id) {
        camisaRepositorio.deleteById(id);
    }
}
