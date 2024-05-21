package com.example.security.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")

    private String secret;

    public String generateToken(Usuario usuario) { //Recebe um usuario e verifica o token dele
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);  // Recebe uma secret. A secret faz com que o hash seja unico na aplicação
            String token = JWT.create()
                    .withIssuer("GKS-BACK")  // Quem foi o emissor do token
                    .withSubject(usuario.getNome())  // Usuario que recebe o token
                    .withExpiresAt(genExpirationDate())  // Tempo que expira o token
                    .sign(algorithm); // Faz a assinatura e a geração final
            return token;
        } catch (JWTCreationException exception){ // Pode lançar essa exception quando um dos parametros a cima não estiver no formato esperado
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("GK-BACK")
                    .build()
                    .verify(token)// Descriptografa o token
                    .getSubject(); // pega o subject (usuario.getNome())
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
