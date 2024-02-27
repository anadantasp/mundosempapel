package br.com.fiap.mundosempapel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mundosempapel.model.Usuario;
import br.com.fiap.mundosempapel.model.dto.Credenciais;
import br.com.fiap.mundosempapel.model.dto.Token;
import br.com.fiap.mundosempapel.model.dto.UsuarioResponse;
import br.com.fiap.mundosempapel.repository.UsuarioRepository;
import br.com.fiap.mundosempapel.service.TokenService;
import jakarta.validation.Valid;


@RestController
public class UsuarioController {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Credenciais credenciais){
        authManager.authenticate(credenciais.toAuthentication());
        return ResponseEntity.ok( tokenService.generateToken(credenciais.email()) );
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.fromUsuario(usuario));
    }
    
}
