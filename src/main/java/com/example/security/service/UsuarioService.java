package com.example.security.service;

import com.example.security.models.Usuario;
import com.example.security.repository.CamisaRepositorio;
import com.example.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Listar todas as camisas
    public List<Usuario> listarTodosUsuario() {
        return usuarioRepositorio.findAll();
    }

    // Obter um usuario pelo ID
    public Usuario obterUsuarioPorId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(id);
        return optionalUsuario.orElse(null);
    }

    // Adicionar um novo usuario
    public Usuario adicionarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Atualizar uma camisa existente
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Deletar um usuario pelo ID
    public void excluirUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
