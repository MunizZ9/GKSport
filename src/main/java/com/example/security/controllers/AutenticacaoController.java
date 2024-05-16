package com.example.security.controllers;

import com.example.security.models.usuario.AuthenticationDTO;
import com.example.security.models.usuario.RegisterDTO;
import com.example.security.models.usuario.Usuario;
import com.example.security.repository.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){ // Faz a verificação da senha do usuario
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.nome(), data.senha());
        var authentication = this.authenticationManager.authenticate(usernamePassword); // usa o BCryptPasswordEncoder para criptorgrafar a senha que ele receber por
                                                                                        // parametro e compara com o Hash da senha que ja tem no banco de dados

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.usuarioRepositorio.findByNome(data.nome()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.nome(), encryptedPassword, data.role());

        this.usuarioRepositorio.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
