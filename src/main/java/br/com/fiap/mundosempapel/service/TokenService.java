package br.com.fiap.mundosempapel.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.mundosempapel.model.dto.Token;

@Service
public class TokenService {

    public Token generateToken(String email){

        Algorithm algorithm = Algorithm.HMAC512("meusecretsupersecreto");

        var jwt = JWT.create().withIssuer("mundosempapel")
        .withSubject(email)
        .withExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
        .sign(algorithm);

        return new Token(jwt, "JWT", "Bearer");
        
    }
    
}
